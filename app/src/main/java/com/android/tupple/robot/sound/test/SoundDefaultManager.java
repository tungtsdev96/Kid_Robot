package com.android.tupple.robot.sound.test;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import com.android.tupple.robot.R;

import static com.android.tupple.robot.sound.test.SoundConstant.MAX_STREAMS;
import static com.android.tupple.robot.sound.test.SoundConstant.SOUND_CLICK;
import static com.android.tupple.robot.sound.test.SoundConstant.SOUND_CORRECT;

/**
 * Created by tungts on 11/24/2017.
 */

public class SoundDefaultManager implements SoundPool.OnLoadCompleteListener{

    private Context context;
    private static SoundDefaultManager defaultSoundManager;
    private SoundPool defaultSoundPool;
    private int soundClick;
    private int soundCorrect;
    private int soundInCorrect;
    private int soundWrong;
    private float volume;

    //default
    private SoundDefaultManager(Context context){
        createSoundPool();
        this.context = context;
        this.soundClick = this.defaultSoundPool.load(context, R.raw.click,1);
        this.soundCorrect = this.defaultSoundPool.load(context, R.raw.test_true,1);
        this.soundInCorrect =  this.defaultSoundPool.load(context, R.raw.test_false, 1);
        this.soundWrong = this.defaultSoundPool.load(context, R.raw.test_wrong, 1);
    }

    public static SoundDefaultManager getDefaultSoundManager(Context context){
        if (defaultSoundManager == null){
            defaultSoundManager = new SoundDefaultManager(context);
        }
        return defaultSoundManager;
    }

    private void createSoundPool() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
//            builder.setAudioAttributes(audioAttributes).setMaxStreams(Constant.MAX_STREAMS);
            this.defaultSoundPool = builder.build();
        } else {
            defaultSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void play(int type){
//        float volume = VolumeController.getCurentVolume(context)* SettingVolume.getInstance(context).getVolumeItem()/100;
//        Toast.makeText(context, VolumeController.getCurentVolume(context)+" " +SettingVolume.getInstance(context).getVolumeItem() , Toast.LENGTH_SHORT).show();
        switch (type){
            case SOUND_CLICK:
                defaultSoundPool.play(this.soundClick,volume, volume, 1, 0, 1);
                break;
            case SOUND_CORRECT:
                defaultSoundPool.play(this.soundCorrect,volume, volume, 1, 0, 1);
                break;
            case SoundConstant.SOUND_INCORRECT:
                defaultSoundPool.play(this.soundInCorrect,volume, volume, 1, 0, 1);
                break;
            case SoundConstant.SOUND_WRONG:
                defaultSoundPool.play(this.soundWrong,volume, volume, 1, 0, 1);
                break;
        }
    }

    public void release(){
        defaultSoundPool.release();
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
