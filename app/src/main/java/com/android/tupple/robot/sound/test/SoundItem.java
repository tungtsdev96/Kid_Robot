//package com.android.tupple.robot.sound;
//
//import android.content.Context;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.util.Log;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * Created by tungts on 12/18/2017.
// */
//
//public class SoundItem implements MediaPlayer.OnCompletionListener {
//
//    private MediaPlayer mediaPlayer;
//    private Context context;
//
//    public SoundItem(Context context){
//        this.context = context;
////        mediaPlayer = new MediaPlayer();
////        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
////        mediaPlayer.setOnCompletionListener(this);
//    }
//
//    FileInputStream fis = null;
//    public void play(byte[] mp3SoundByteArray, String name) {
//        try {
//            File f = FileController.getInstance().checkDir(Config.PACKAGE+"/cache");
//            // create temp file that will hold byte array
//
//            File tempMp3 = File.createTempFile(name, ".mp3",f);
//            tempMp3.deleteOnExit();
//            FileOutputStream fos = new FileOutputStream(tempMp3);
//            fos.write(mp3SoundByteArray);
//            fos.close();
//
//            // resetting mediaplayer instance to evade problems
//            mediaPlayer = null;
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setOnCompletionListener(this);
//            mediaPlayer.reset();
//
//            // In case you run into issues with threading consider new instance like:
//            fis = new FileInputStream(tempMp3);
//            mediaPlayer.setDataSource(fis.getFD());
//            float volume = VolumeController.getCurentVolume(context)* SettingVolume.getInstance(context).getVolumeItem()/100;
//            mediaPlayer.setVolume(volume,volume);
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException ex) {
//            String s = ex.toString();
//            Log.e("sound",s);
//            ex.printStackTrace();
//        } finally {
//            if (fis != null){
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mediaPlayer) {
//        mediaPlayer.release();
//    }
//}
