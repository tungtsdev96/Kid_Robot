package com.android.tupple.robot.common.music;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by tungts on 3/22/20.
 */

public class MultiPlayer implements
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener,
        AudioManager.OnAudioFocusChangeListener {

    public interface MultiPlayerListener {
        void onError();
        void onCompletion();
    }

    private final String TAG = "MultiPlayer";

    private WeakReference<Context> mContext;
    private MediaPlayer mCurrentMediaPlayer;
    private MultiPlayerListener mMultiPlayerListener;

    public MultiPlayer(Context context) {
        mContext = new WeakReference<>(context);
        innitMediaPlayer();
    }

    private void setMultiPlayerListener(MultiPlayerListener multiPlayerListener) {
        this.mMultiPlayerListener = multiPlayerListener;
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
        if (mMultiPlayerListener != null) {
            mMultiPlayerListener.onCompletion();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d(TAG, "Override " + mp);
        if (mMultiPlayerListener != null) {
            mMultiPlayerListener.onError();
        }
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared");
        mp.start();
    }

    public void play(String url) {
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
