package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;

public class AudioPlayerActivity extends BaseActivity {
    SeekBar mSeekbar;
    TextView txtCurrentTime, txtTotalTime;
    ImageView playIcon, backIcon, previousIcon, imageBackground;
    static MediaPlayer mMediaPlayer;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_audio_player;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initView();
        String url = "";
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("test");
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();

        }
        initPlayer(url);
        play();

        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

    }

    protected void initView() {
        mSeekbar = findViewById(R.id.seekbar);
        txtCurrentTime = findViewById(R.id.current_time);
        txtTotalTime = findViewById(R.id.total_time);
        playIcon = findViewById(R.id.play_pause);
        backIcon = findViewById(R.id.back_button);
        previousIcon = findViewById(R.id.next_button);
        imageBackground = findViewById(R.id.image_background);
    }

    protected void initPlayer(String url) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        Uri audioUri = Uri.parse(url);
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), audioUri);
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


    private void play() {

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

    public String createTimeLabel(int duration) {
        String timeLabel = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        timeLabel += min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;


    }

    @Override
    protected void onPause() {
        mMediaPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMediaPlayer.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
