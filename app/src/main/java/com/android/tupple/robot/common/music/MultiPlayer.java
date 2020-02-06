package com.android.tupple.robot.common.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;

import java.lang.ref.WeakReference;

public class MultiPlayer implements
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        AudioManager.OnAudioFocusChangeListener{

    private final String TAG = "MultiPlayer";

    private WeakReference<Context> mContext;

    private MediaPlayer mCurrentMediaPlayer;

    public MultiPlayer(Context context) {
        mContext = new WeakReference<>(context);
        innitMediaPlayer();
    }

    private void innitMediaPlayer() {
        mCurrentMediaPlayer = new MediaPlayer();
        mCurrentMediaPlayer.setWakeMode(mContext.get(), PowerManager.PARTIAL_WAKE_LOCK);
        mCurrentMediaPlayer.setOnPreparedListener(this);
        mCurrentMediaPlayer.setOnCompletionListener(this);
        mCurrentMediaPlayer.setOnErrorListener(this);
        mCurrentMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(TAG, "onCompletion " + mp);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "Override " + mp);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared");
        mp.start();
    }

    public void playSong(String url) {
        if (mCurrentMediaPlayer != null) {
            mCurrentMediaPlayer.reset();
            try {
                Log.d(TAG,url);
                mCurrentMediaPlayer.setDataSource(url);
                mCurrentMediaPlayer.prepareAsync();
            } catch (Exception e) {
                Log.e(TAG, "Error playing from data source", e);
            }
        }
    }

    public void pause() {
        if (mCurrentMediaPlayer != null && mCurrentMediaPlayer.isPlaying()) {
            mCurrentMediaPlayer.pause();
        }
    }

    public void stop() {
        if (mCurrentMediaPlayer != null) {
            mCurrentMediaPlayer.stop();
        }
    }

    public boolean isPlaying() {
        if (mCurrentMediaPlayer != null) {
            return mCurrentMediaPlayer.isPlaying();
        }
        return false;
    }

    public void destroy() {
        if (mCurrentMediaPlayer != null) {
            mCurrentMediaPlayer.stop();
            mCurrentMediaPlayer.release();
        }
    }
}
