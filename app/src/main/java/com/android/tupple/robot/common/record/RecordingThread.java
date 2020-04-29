package com.android.tupple.robot.common.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;

/**
 * Created by tungts on 4/22/20.
 */

public class RecordingThread {

    private static final String TAG = RecordingThread.class.getSimpleName();
    private static final int SAMPLE_RATE = 8000;
    private static final int BUFFER_SIZE = 8000;

    private short[] mResults = new short[40000];
    private boolean mShouldContinue;
    private AudioDataReceivedListener mAudioDataReceivedListener;
    private Thread mThread;


    public RecordingThread(){}

    public void setAudioDataReceivedListener(AudioDataReceivedListener listener) {
        this.mAudioDataReceivedListener = listener;
    }

    public void startRecording() {
        if (mThread != null) {
            return;
        }

        mResults = new short[40000];
        mTotalRecord = 0;
        mShouldContinue = true;
        mThread = new Thread(this::record);
        mThread.start();
    }

    public void stopRecording() {
        Log.d(TAG, "stopRecording");
        if (mThread == null)
            return;

        mShouldContinue = false;
        mThread = null;
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

        if (mAudioDataReceivedListener != null) {
            mAudioDataReceivedListener.onAudioDataReceived(mResults);
        }
    }

    private int mTotalRecord = 0;
    private void addItemRecord(short[] audioBuffer) {
//        if (mAudioDataReceivedListener != null) {
//            mAudioDataReceivedListener.onDataPerSecond(audioBuffer, mTotalRecord == 0);
//        }
        System.arraycopy(audioBuffer, 0, mResults, mTotalRecord, audioBuffer.length);
        mTotalRecord += audioBuffer.length;
        if (mTotalRecord == BUFFER_SIZE * 5) {
            stopRecording();
        }
        Log.d(TAG, mTotalRecord + " " + mResults.length);
    }

    public interface AudioDataReceivedListener {
        void onAudioDataReceived(short[] data);

        void onDataPerSecond(short[] buffer, boolean isStart);
    }

}
