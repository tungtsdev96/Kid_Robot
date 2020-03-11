package com.android.tupple.robot.view.audioplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerView;
import com.android.tupple.robot.utils.GlideUtils;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AudioPlayerViewImpl implements AudioPlayerView<Media> {
    private Activity mActivity;
    SeekBar mSeekbar;
    TextView txtCurrentTime, txtTotalTime, txtMediaTitle;
    ImageView imageBackground;
    FloatingActionButton go_back, btnPrevious, btnNext, btnPlayStop;

    static MediaPlayer mMediaPlayer;
    private Media mCurrentAudio;

    private CleanObserver mCloseButtonClickedObserver;
    private CleanObserver mNextButtonClickedObserver;
    private CleanObserver mPreviousButtonClickedObserver;
    private CleanObserver mStopPlayButtonClickedObserver;


    public AudioPlayerViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void initLayout() {
        mSeekbar = mActivity.findViewById(R.id.seekbar);
        txtCurrentTime = mActivity.findViewById(R.id.current_time);
        txtTotalTime = mActivity.findViewById(R.id.total_time);
        btnPlayStop = mActivity.findViewById(R.id.btn_play);
        btnPrevious = mActivity.findViewById(R.id.btn_previous);
        btnNext = mActivity.findViewById(R.id.btn_next);
        imageBackground = mActivity.findViewById(R.id.image_background);
        go_back = mActivity.findViewById(R.id.btn_close);
        txtMediaTitle = mActivity.findViewById(R.id.audioname);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCloseButtonClickedObserver.onNext();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreviousButtonClickedObserver.onNext();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNextButtonClickedObserver.onNext();
            }
        });
        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStopPlayButtonClickedObserver.onNext();
            }
        });

    }


    @Override
    public void setCurrentAudio(Media media) {
        this.mCurrentAudio = media;
        Glide.with(mActivity).load(media.getAudio_thumbnail()).into(imageBackground);
        Log.d("AudioPlayerViewImpl", media.getAudio_thumbnail());
        txtMediaTitle.setText(media.getTitle());
    }

    @Override
    public void preparePlayer() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        Uri audioUri = Uri.parse(mCurrentAudio.getMedia_url());
        Log.d("AudioPlayerViewImpl", mCurrentAudio.getMedia_url());
        mMediaPlayer = MediaPlayer.create(mActivity, audioUri);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mMediaPlayer.getDuration());
                txtTotalTime.setText(totalTime);
                mSeekbar.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
                btnPlayStop.setImageResource(R.drawable.stop_button);

            }
        });
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mSeekbar.setProgress(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mMediaPlayer != null) {
                    try {
                        if (mMediaPlayer.isPlaying()) {
                            Message msg = new Message();
                            msg.what = mMediaPlayer.getCurrentPosition();
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void playAudio() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            btnPlayStop.setImageResource(R.drawable.stop_button);
        }
    }

    @Override
    public void changeIconStopPlay(boolean isPlay) {
        if (isPlay) {
            btnPlayStop.setImageResource(R.drawable.stop_button);
        } else btnPlayStop.setImageResource(R.drawable.play_button);
    }

    @Override
    public void pauseAudio() {
        pause();
    }

    @Override
    public void stopAudio() {
        mMediaPlayer.stop();
    }


    private void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            btnPlayStop.setImageResource(R.drawable.play_button);

        }

    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mCloseButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getNextButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mNextButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getPreviousButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mPreviousButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getStopPlayButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mStopPlayButtonClickedObserver = cleanObserver);

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int current_position = msg.what;
            mSeekbar.setProgress(current_position);
            String cTime = createTimeLabel(current_position);
            txtCurrentTime.setText(cTime);
        }
    };

    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;


    }
}
