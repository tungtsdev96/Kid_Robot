package com.android.tupple.robot.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.constant.EntertainmentConstant;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeVideoPlayerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String YoutubeVideoID = "";
    YouTubePlayerView youTubePlayerView;
    public static final int REQUEST_CODE =12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "";
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(EntertainmentConstant.VIDEO_INTENT);
        }
        YoutubeVideoID = getYouTubeId(url);
        setContentView(R.layout.activity_youtube_video_player);
        youTubePlayerView = findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(EntertainmentConstant.YOUTUBE_API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(YoutubeVideoID);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(YoutubeVideoPlayerActivity.this,12);
        }else {
            Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE){
            youTubePlayerView.initialize(EntertainmentConstant.YOUTUBE_API_KEY,this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private static String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }
}
