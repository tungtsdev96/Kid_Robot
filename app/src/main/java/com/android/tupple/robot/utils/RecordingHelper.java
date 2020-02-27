package com.android.tupple.robot.utils;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by tungts on 2020-02-23.
 */

// TODO check permission, delete cache
public class RecordingHelper {

    private final String TAG = "RecordingHelper";

    private WeakReference<Context> mContext;
    private MediaRecorder mRecorder;

    private static RecordingHelper mInstance;

    private RecordingHelper() {
    }

    public static RecordingHelper newInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RecordingHelper();
        }
        return mInstance.setContext(context);
    }

    private RecordingHelper setContext(Context context) {
        mContext = new WeakReference<>(context);
        return mInstance;
    }

    public File startRecorder(String fileName) {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
        }

        File temp;
        File cacheRecording = new File(mContext.get().getCacheDir(),"record");
        if (!cacheRecording.exists()) {
            boolean isSuccess = cacheRecording.mkdir();
        }

        temp = new File(cacheRecording + "/" + fileName + ".mp3");
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(temp.getAbsolutePath());
        try {
            mRecorder.prepare();
            mRecorder.start();
            Log.d(TAG, "Start Recording " + temp.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "" + e.getLocalizedMessage());
            return startRecorder(fileName);
        }
        return temp;
    }

    public void stop() {
        if (mRecorder == null) {
            return;
        }

        Log.d(TAG, "Stop Recording");
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
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
