package com.android.tupple.robot.common.sound;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;

/**
 * Created by tungts on 2020-03-06.
 */

public class SoundPoolManagement {

    private final String TAG = "SoundPoolManagement";

    private static SoundPoolManagement mInstance;
    private SoundPool mSoundPool;

    // <vocabId, path audio>
    private SparseIntArray mListSoundItem = new SparseIntArray();

    public static SoundPoolManagement getInstance() {
        if (mInstance == null) {
            mInstance = new SoundPoolManagement();
        }
        return mInstance;
    }

    public void clear() {
        release();
        mListSoundItem.clear();
        mInstance = null;
    }

    private void release() {
        mListSoundItem.clear();
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    public SoundPoolManagement init() {
        release();

        if (mSoundPool == null) {
            createSoundPool();
        }
        return mInstance;
    }

    private void createSoundPool() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(SoundConstant.MAX_STREAMS);
            this.mSoundPool = builder.build();
        } else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            mSoundPool = new SoundPool(SoundConstant.MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public SoundPoolManagement loadSound(SparseArray<String> listSoundItem) {
        for (int i = 0; i < listSoundItem.size(); i++) {
            int key = listSoundItem.keyAt(i);
            int idSound = mSoundPool.load(listSoundItem.get(key), 1);
            Log.d(TAG, "key: " + key + " path: " + listSoundItem.get(key) + " idSound: " + idSound);
            mListSoundItem.put(key, idSound);
        }
        return mInstance;
    }

    public void playSound(int key) {
        if (mSoundPool != null && mListSoundItem.get(key) != 0) {
            mSoundPool.play(mListSoundItem.get(key), 1f, 1f, 1, 0, 1f);
        }
    }

}
