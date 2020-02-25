package com.android.tupple.robot.utils;

import android.content.Context;
import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by tungts on 2020-02-23.
 */

// TODO check permission
public class RecordingHelper {

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

        File temp = null;
        try {
            temp = File.createTempFile(fileName, "mp3", mContext.get().getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
            return startRecorder(fileName);
        }

        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(temp.getAbsolutePath());
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
            return startRecorder(fileName);
        }
        return temp;
    }

    public void stop() {
        if (mRecorder == null) {
            return;
        }

        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public int getAmplitude() {
        if (mRecorder != null) {
            return mRecorder.getMaxAmplitude();
        }
        return -1;
    }

}
