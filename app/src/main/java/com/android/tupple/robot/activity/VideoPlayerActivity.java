package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

public class VideoPlayerActivity extends BaseActivity {
    private static SimpleExoPlayer player;
    private static PlayerView playerView;


    @Override
    protected int getLayoutContent() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initView();
        String url = "";
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("test");
        }

        playVideoInLocalStorage(url);
    }

    protected void initView() {
        playerView = findViewById(R.id.player);
    }


    public void playVideoInWebDatabase(String url) {

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);


        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,
                Util.getUserAgent(VideoPlayerActivity.this, "Media Player"));
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url));

        player.prepare(mediaSource);

        player.setPlayWhenReady(true);
    }

    public void playVideoInLocalStorage(String url) {
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);


        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,
                Util.getUserAgent(VideoPlayerActivity.this, "Media Player"));
        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(new File(url).toString()));
        player.prepare(mediaSource);

        player.setPlayWhenReady(true);
    }


    @Override
    protected void onStop() {
        playerView.setPlayer(null);
        player.release();
        player = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
