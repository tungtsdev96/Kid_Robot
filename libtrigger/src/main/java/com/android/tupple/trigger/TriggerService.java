package com.android.tupple.trigger;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.tupple.trigger.audio.MFCC;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriggerService extends Service {

    public static final String TAG = TriggerService.class.getSimpleName();
    public static final String DIS_WAKE_UP_WORD = "DIS_WAKE_UP_WORD";
    public static final String ALLOW_WAKE_UP_WORD = "ALLOW_WAKE_UP_WORD";

    public static final String INTENT_STICKER = "test.Broadcast.sticker.mcd";

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
    public static final int SAMPLE_DURATION_MS = 1000;
    public static final int RECORDING_LENGTH = (SAMPLE_RATE * SAMPLE_DURATION_MS / 1000);
    public static final String MODEL_FILENAME = "file:///android_asset/hey_tuple_49.pb";
    public static final String INPUT_DATA_NAME = "input_1";
    public static final String OUTPUT_SCORES_NAME = "dense_2/Softmax";

    List<Short> recordingBuffer = new ArrayList<>();
    boolean shouldContinue = true;
    boolean shouldContinueRecognition = true;
    private Thread recordingThread;
    private Thread recognitionThread;
    private TensorFlowInferenceInterface inferenceInterface;
    MFCC mfcc = new MFCC();


    private int numberLoadIgnore = 0;


    @Override
    public void onCreate() {
        super.onCreate();


        Log.d(TAG, "onCreate");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ALLOW_WAKE_UP_WORD);
        intentFilter.addAction(DIS_WAKE_UP_WORD);

        registerReceiver(ReceiverWake, intentFilter);

        intiListRecord();

        //wake up word

        inferenceInterface = new TensorFlowInferenceInterface(getAssets(), MODEL_FILENAME);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "OnstartCommand");
        try {

        } catch (Exception e) {
            Log.i(TAG, "onStartCommand/Exception", e);
        }

        return START_STICKY;
    }

    /**
     * A client is binding to the service with bindService()
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return mBinder;
    }

    /**
     * Called when all clients have unbound with unbindService()
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return mAllowRebind;
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
        unregisterReceiver(ReceiverWake);
//        recordingBuffer.clear();
//        result.clear();
//        checkResult.clear();
    }


    //wake up word
    private void addItemRecord(short[] newItems) {
        for (short i : newItems) {
            recordingBuffer.add(i);
            recordingBuffer.remove(0);
        }

    }

    private void intiListRecord() {

        for (int i = 0; i < RECORDING_LENGTH; i++) {
            recordingBuffer.add((short) 0);
        }
    }


    public synchronized void startRecording() {
        if (recordingThread != null) {
            return;
        }
        shouldContinue = true;
        recordingThread = new Thread(this::record);
        recordingThread.start();
    }

    public synchronized void stopRecording() {
        if (recordingThread == null) {
            return;
        }
        shouldContinue = false;
        recordingThread = null;
    }

    private void record() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_AUDIO);

        int bufferSize = 4800;
        short[] audioBuffer = new short[bufferSize];

        //fixme check again 015
        AudioRecord record =
                new AudioRecord(
                        MediaRecorder.AudioSource.MIC,
                        SAMPLE_RATE,
                        AudioFormat.CHANNEL_IN_MONO,
                        AudioFormat.ENCODING_PCM_16BIT,
                        bufferSize);

        if (record.getState() != AudioRecord.STATE_INITIALIZED) {
            return;
        }

        record.startRecording();

        // Loop, gathering audio data and copying it to a round-robin buffer.
        while (shouldContinue) {
            int numberRead = record.read(audioBuffer, 0, audioBuffer.length);

//            Log.d(TAG, "recognize done: ");
            addItemRecord(audioBuffer);
            synchronized (this){
                this.notify();
            }
        }

        record.stop();
        record.release();
    }

    public synchronized void startRecognition() {
        if (recognitionThread != null) {
            return;
        }
        shouldContinueRecognition = true;
        recognitionThread = new Thread(this::recognize);
        recognitionThread.start();
    }

    public synchronized void stopRecognition() {
        if (recognitionThread == null) {
            return;
        }
        shouldContinueRecognition = false;
        recognitionThread = null;
    }

    private void recognize() {

            Short[] inputBuffer = new Short[RECORDING_LENGTH];
            double[] floatInputBuffer = new double[RECORDING_LENGTH];
            float[] outputScores = new float[2];
            String[] outputScoresNames = new String[]{OUTPUT_SCORES_NAME};

            // Loop, grabbing recorded data and running the recognition model on it.
            while (shouldContinueRecognition) {
                // The recording thread places data in this round-robin buffer, so lock to
                // make sure there's no writing happening and then copy it to our own
                // local version
                synchronized (this){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.arraycopy(recordingBuffer.toArray(new Short[RECORDING_LENGTH]), 0, inputBuffer, 0, RECORDING_LENGTH);

                float maxInput = -1;
//                Log.d(TAG, String.valueOf(inputBuffer[0]));
                for (int i = 0; i < inputBuffer.length; i++) {

                    if (maxInput < inputBuffer[i]) {
                        maxInput = inputBuffer[i];
                    }
                }

                if (maxInput == 0) {
                    maxInput = 32767.0f;
                }
    //             We need to feed in float values between -1.0f and 1.0f, so divide the
    //             signed 16-bit inputs.
                for (int i = 0; i < RECORDING_LENGTH; ++i) {
                    floatInputBuffer[i] = inputBuffer[i] / maxInput;
                }


                double[][] data = swapAxis(mfcc.processTemp(floatInputBuffer));

                float[] resultMfcc = convert2To1(data);
                try {
                    inferenceInterface.feed(INPUT_DATA_NAME, resultMfcc, 1, 101, 40, 1);
                    inferenceInterface.run(outputScoresNames);
                    inferenceInterface.fetch(OUTPUT_SCORES_NAME, outputScores);

                    Log.d(TAG, "recognize: " + outputScores[0] + "  " + outputScores[1]);


                    if (outputScores[1] > 0.7f && numberLoadIgnore == 0){
                        numberLoadIgnore = 2;
                        stopRecording();
                        stopRecognition();

                        Intent intent = new Intent("tungts");
                        intent.putExtra("text", String.valueOf(outputScores[1]));
                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                        stopSelf();
                    }
                    if (numberLoadIgnore > 0) {
                        numberLoadIgnore -= 1;
                    }

                    // We don't need to run too frequently, so snooze for a bit.
    //                Thread.sleep(MINIMUM_TIME_BETWEEN_SAMPLES_MS);
                } catch (Exception e) {
                    // Ignore
                }
        }

    }


    public void writeStringAsFile(final Short[] fileContents, String fileName) {
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), fileName));
            for (int i : fileContents) {
                out.write(i + ",");
            }
            out.close();
        } catch (IOException e) {
        }
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
            int colum = list[0].length;

            double[][] result = new double[colum][row];

            for (int i = 0; i < colum; i++) {
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

    BroadcastReceiver ReceiverWake = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == null) {
                return;
            }

            if (intent.getAction().equals(DIS_WAKE_UP_WORD)) {
                Log.d(TAG, "onReceive: stop record");
                stopRecording();
                stopRecognition();
            } else {
//                if (isPermissionAllow(Manifest.permission.RECORD_AUDIO)) {
                    Log.d(TAG, "onReceive: start record");
                    startRecording();
                    startRecognition();
//                }
            }

        }
    };

    private OnTriggerReceiver mOnTriggerReceiver;

    public void setmOnTriggerReceiver(OnTriggerReceiver onTriggerReceiver) {
        this.mOnTriggerReceiver = onTriggerReceiver;
    }

    public interface OnTriggerReceiver {

        void onTriggerRecognized(String output);

    }
}
