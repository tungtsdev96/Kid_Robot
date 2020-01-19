package com.android.tupple.robot.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.WindowManagerUtils;
import com.android.tupple.trigger.TriggerService;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

import static com.android.tupple.trigger.TriggerService.ALLOW_WAKE_UP_WORD;

/**
 * Created by tungts on 2020-01-12.
 */

// TODO Calculate battery consumption if always run this activity

public class FaceSmileActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_smile);

        GifImageView mGifImageView = findViewById(R.id.img_gif_face);
        mGifImageView.setImageResource(R.drawable.face_smile);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            WindowManagerUtils.setFullScreenMode(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, new IntentFilter("tungts"));
        startTriggerService();

        new Handler().postDelayed(() -> sendBroadcast(new Intent(ALLOW_WAKE_UP_WORD)), 500);
    }

    private void startTriggerService() {
        Intent intent = new Intent(this, TriggerService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), "tungts")) {
                startRecordingActivity();
            }
        }
    };


    private void startRecordingActivity() {
        startActivity(new Intent(this, RecordingActivity.class));
    }
}
