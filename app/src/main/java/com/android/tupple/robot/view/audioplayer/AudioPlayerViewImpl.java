package com.android.tupple.robot.view.audioplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayerViewImpl implements AudioPlayerView<Media> {
    private Activity mActivity;
    SeekBar mSeekbar;
    TextView txtCurrentTime, txtTotalTime, txtMediaTitle;
    ImageView playIcon, backIcon, previousIcon, imageBackground;
    FloatingActionButton go_back;

    private CleanObserver mCloseButtonClickedObserver;
    private CleanObserver mNextButtonClickedObserver;
    private CleanObserver mPreviousButtonClickedObserver;

    static MediaPlayer mMediaPlayer;

    public AudioPlayerViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void initLayout() {
        mSeekbar = mActivity.findViewById(R.id.seekbar);
        txtCurrentTime = mActivity.findViewById(R.id.current_time);
        txtTotalTime = mActivity.findViewById(R.id.total_time);
        playIcon = mActivity.findViewById(R.id.play_pause);
        backIcon = mActivity.findViewById(R.id.back_button);
        previousIcon = mActivity.findViewById(R.id.next_button);
        imageBackground = mActivity.findViewById(R.id.image_background);
        go_back = mActivity.findViewById(R.id.btn_close);
        txtMediaTitle = mActivity.findViewById(R.id.audioname);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCloseButtonClickedObserver.onNext();
            }
        });
    }

    @Override
    public void getListAudio() {

    }

    @Override
    public void preparePlayer(Media media) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        Uri audioUri = Uri.parse(media.getMedia_url());
        mMediaPlayer = MediaPlayer.create(mActivity, audioUri);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                String totalTime = createTimeLabel(mMediaPlayer.getDuration());
                txtTotalTime.setText(totalTime);
                mSeekbar.setMax(mMediaPlayer.getDuration());
                mMediaPlayer.start();
                playIcon.setImageResource(R.drawable.stop_button);

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
//                        Log.i("Thread ", "Thread Called");
                        // create new message to send to handler
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
    public void playCurrentAudio(Media media) {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            playIcon.setImageResource(R.drawable.stop_button);
        } else {
            pause();
        }
    }

    private void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            playIcon.setImageResource(R.drawable.play_button);

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
