package com.android.tupple.trigger;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import com.android.tupple.trigger.audio.MFCC;
import com.android.tupple.trigger.recording.RecordingActivity;
import com.android.tupple.trigger.utils.TriggerConstant;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tungts on 2020-02-01.
 */

public class TriggerService extends Service {

    public static final String TAG = TriggerService.class.getSimpleName();
    public static final String DIS_WAKE_UP_WORD = "DIS_WAKE_UP_WORD";
    public static final String ALLOW_WAKE_UP_WORD = "ALLOW_WAKE_UP_WORD";

    /**
     * inf for clients that bind
     */
    IBinder mBinder;
    /**
     * indicates whether onRebind should be used
     */
    boolean mAllowRebind;

    //wake up word
    public static final int SAMPLE_RATE = 16000;
    private static final int BUFFER_SIZE = 4800;
    public static final String MODEL_FILENAME = "file:///android_asset/tuple_v1.pb";
    public static final String INPUT_DATA_NAME = "input_1";
    public static final String OUTPUT_SCORES_NAME = "dense_2/Softmax";
    private final ReentrantLock mRecordingBufferLock = new ReentrantLock();

    private short[] mRecordingBuffer = new short[SAMPLE_RATE];
    private Thread mRecordingThread;
    private boolean mShouldContinue = true;
    private Thread mRecognitionThread;
    private boolean mShouldContinueRecognition = true;
    private TensorFlowInferenceInterface inferenceInterface;
    private MFCC mfcc = new MFCC();
    private int numberLoadIgnore = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate " + Thread.currentThread().getName());
        registerReceiverTrigger();
        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILENAME);
    }

    private void registerReceiverTrigger() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ALLOW_WAKE_UP_WORD);
        intentFilter.addAction(DIS_WAKE_UP_WORD);
        registerReceiver(mReceiverWake, intentFilter);
    }

    BroadcastReceiver mReceiverWake = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null) {
                return;
            }
            if (intent.getAction().equals(DIS_WAKE_UP_WORD)) {
                stopRecording();
                stopRecognition();
            } else {
                startRecording();
                startRecognition();
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnStartCommand " + Thread.currentThread().getName());
        return START_STICKY;
    }

    /**
     * A client is binding to the service with bindService()
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    /**
     * Called when all clients have unbound with unbindService()
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * Called when a client is binding to the service with bindService()
     */
    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind");
    }

    /**
     * Called when The service is no longer used and is being destroyed
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        unregisterReceiver(mReceiverWake);
    }

    //This is where we detect the app is being killed, thus stop service.
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "onTaskRemoved ");
        stopSelf();
    }

    private void addItemRecord(short[] newItems) {
        // We store off all the data for the recognition thread to access. The ML
        // thread will copy out of this buffer into its own, while holding the
        // lock, so this should be thread safe.
        mRecordingBufferLock.lock();
        try {
            System.arraycopy(mRecordingBuffer, BUFFER_SIZE, mRecordingBuffer, 0, SAMPLE_RATE - BUFFER_SIZE);
            System.arraycopy(newItems, 0, mRecordingBuffer, SAMPLE_RATE - BUFFER_SIZE, BUFFER_SIZE);
        } finally {
            mRecordingBufferLock.unlock();
        }
    }

    public synchronized void startRecording() {
        if (mRecordingThread != null) {
            return;
        }
        Log.d(TAG, "Start Recording");
        mShouldContinue = true;
        mRecordingThread = new Thread(this::record);
        mRecordingThread.start();
    }

    private void record() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);
        short[] audioBuffer = new short[BUFFER_SIZE];
        int sourceAudio = MediaRecorder.AudioSource.MIC;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sourceAudio = MediaRecorder.AudioSource.UNPROCESSED;
        }

        AudioRecord record = new AudioRecord(sourceAudio,
                SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                BUFFER_SIZE);

        if (record.getState() != AudioRecord.STATE_INITIALIZED) {
            return;
        }

        record.startRecording();

        while (mShouldContinue) {
            record.read(audioBuffer, 0, audioBuffer.length);
            addItemRecord(audioBuffer);
        }

        record.stop();
        record.release();
    }

    public synchronized void startRecognition() {
        if (mRecognitionThread != null) {
            return;
        }
        mShouldContinueRecognition = true;
        mRecognitionThread = new Thread(this::recognizeTrigger);
        mRecognitionThread.start();
    }

    private void recognizeTrigger() {
        Log.e(TAG, "recognizeTrigger");

        short[] inputBuffer = new short[SAMPLE_RATE];
        double[] floatInputBuffer = new double[SAMPLE_RATE];
        float[] outputScores = new float[7];
        String[] outputScoresNames = new String[]{OUTPUT_SCORES_NAME};

        float maxInput = -1;
        double[][] data;
        float[] resultMfcc;
        float distance = 0;

        while (mShouldContinueRecognition) {
            mRecordingBufferLock.lock();
            try {
                System.arraycopy(mRecordingBuffer, 0, inputBuffer, 0, SAMPLE_RATE);
            } finally {
                mRecordingBufferLock.unlock();
            }

//            cacheInputBuffer(inputBuffer);

            maxInput = -1;
            for (Short aShort : inputBuffer) {
                if (maxInput < aShort) {
                    maxInput = aShort;
                }
            }

            if (maxInput == 0) {
                maxInput = 32767.0f;
            }

            for (int i = 0; i < SAMPLE_RATE; ++i) {
                floatInputBuffer[i] = inputBuffer[i] / maxInput;
            }

            data = swapAxis(mfcc.processTemp(floatInputBuffer));
            resultMfcc = convert2To1(data);

            try {
                inferenceInterface.feed(INPUT_DATA_NAME, resultMfcc, 1, 101, 40, 1);
                inferenceInterface.run(outputScoresNames);
                inferenceInterface.fetch(OUTPUT_SCORES_NAME, outputScores);

                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "recognize: " + outputScores[0] + "  " + outputScores[1]
                            + " " + outputScores[2]
                            + " " + outputScores[3]
                            + " " + outputScores[4]
                            + " " + outputScores[5]);
                }

                if (outputScores[0] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(0);
                    continue;
                }
                if (outputScores[1] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(1);
                    continue;
                }
                if (outputScores[2] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(2);
                    continue;
                }
                if (outputScores[3] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(3);
                    continue;
                }
                if (outputScores[4] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(4);
                    continue;
                }
                if (outputScores[5] > 0.7f && numberLoadIgnore == 0) {
                    pushEventToUI(5);
                    continue;
                }

                if (numberLoadIgnore > 0) {
                    numberLoadIgnore -= 1;
                }

            } catch (Exception e) {
                // Ignore
            }
        }
    }

    private void cacheInputBuffer(short[] inputBuffer) {
        try {
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/input");
            if (!f.exists()) {
                boolean x = f.mkdir();
            }

            File cache = new File(f, String.valueOf(System.currentTimeMillis()));
            FileWriter out = new FileWriter(cache);
            for (int i : inputBuffer) {
                out.write(i + ",");
            }
            out.close();
        } catch (IOException e) {
        }
    }

    private void pushEventToUI(int type) {
        numberLoadIgnore = 2;
        stopRecording();
        stopRecognition();
        startRecordingActivity();
    }

    private void startRecordingActivity() {
        Log.d(TAG, "startRecordingActivity");
        Intent intent = new Intent(this, RecordingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(TriggerConstant.EXTRA_START_FROM_TRIGGER, true);
        startActivity(intent);
    }

    public synchronized void stopRecording() {
        if (mRecordingThread == null) {
            return;
        }
        mShouldContinue = false;
        mRecordingThread = null;
    }

    public synchronized void stopRecognition() {
        if (mRecognitionThread == null) {
            return;
        }
        mShouldContinueRecognition = false;
        mRecognitionThread = null;
    }

    public static float[] convert2To1(double[][] arr) {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add((float) arr[i][j]);
            }
        }

        float[] vector = new float[list.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = list.get(i);
        }
        return vector;
    }

    public static double[][] swapAxis(double[][] list) {

        try {
            int row = list.length;
            int column = list[0].length;

            double[][] result = new double[column][row];

            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    result[i][j] = list[j][i];
                }
            }
            return result;
        } catch (Exception e) {
            //do nothing
        }
        return new double[0][0];
    }

}
