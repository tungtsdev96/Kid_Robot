package com.android.tupple.robot.utils;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by tungts on 2020-02-23.
 */

public class RecordingHelper {

    private final String TAG = "RecordingHelper";

    private Context mContext;
    private MediaRecorder mRecorder;
    private boolean mIsRecording;
    private File mFileTmp = null;

    public RecordingHelper(Context context) {
        mContext = context;
    }

    public void startRecord(String fileName) {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
        }

        File cacheRecording = new File(mContext.getCacheDir(),"record");
        if (!cacheRecording.exists()) {
            boolean isSuccess = cacheRecording.mkdir();
        }

        mFileTmp = new File(cacheRecording + "/" + fileName + ".mp3");
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(mFileTmp.getAbsolutePath());
        try {
            mRecorder.prepare();
            mRecorder.start();
            mIsRecording = true;
            Log.d(TAG, "Start Recording " + mFileTmp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "" + e.getLocalizedMessage());
            startRecord(fileName);
        }
    }

    public boolean isRecording() {
        return mIsRecording;
    }

    public File stopRecord() {
        if (mRecorder == null) {
            return null;
        }

        Log.d(TAG, "Stop Recording " + mFileTmp.getAbsolutePath());
        mIsRecording = false;
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        return mFileTmp;
    }

    public int getAmplitude() {
        if (mRecorder != null) {
            Log.d(TAG, "getAmplitude " + mRecorder.getMaxAmplitude());
            return mRecorder.getMaxAmplitude();
        }
        Log.d(TAG, "getAmplitude -1");
        return -1;
    }

}
