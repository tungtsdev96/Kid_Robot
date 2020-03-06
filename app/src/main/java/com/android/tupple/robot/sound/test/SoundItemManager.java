package com.android.tupple.robot.sound.test;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by tungts on 11/24/2017.
 */

public class SoundItemManager implements SoundPool.OnLoadCompleteListener{

    private static SoundItemManager soundItemManager;
    private SoundPool soundPool;
    private HashMap<String, Integer> mSoundPoolMap;
    private HashMap<String, String> mSoundItem;


    public static SoundItemManager getSoundItemManager(){
        if (soundItemManager == null){
            soundItemManager = new SoundItemManager();
        }
        return soundItemManager;
    }

    private SoundItemManager(){
        createSoundPool();
        mSoundPoolMap = new HashMap<>();
    }

    private void createSoundPool() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttributes).setMaxStreams(SoundConstant.MAX_STREAMS);
            this.soundPool = builder.build();
        } else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(SoundConstant.MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public void resetSoundPoolMap(HashMap<String, Integer> mSoundPoolMap, Context context) {
        this.reset();
        for (String nameItem : mSoundPoolMap.keySet()){
            addSound(context,nameItem,mSoundPoolMap.get(nameItem));
        }
    }

    public void addSound(Context context, String name, int soundId){
        int i = soundPool.load(context, soundId, 1);
        mSoundPoolMap.put(name, i);
    }

    public void addSound(String name, String path){
        int i = soundPool.load(path, 1);
        mSoundItem.put(name, path);
    }

    public void playItemSound(Context context, String name) {
        soundPool.play(mSoundPoolMap.get(name), 1f, 1f, 1, 0, 1f);
    }

    public void reset(){
        soundItemManager.release();
        mSoundPoolMap.clear();
    }

    public void release(){
        soundPool.release();
        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



    @Override
    public void onLoadComplete(SoundPool soundPool, int i, int i1) {

    }
}
