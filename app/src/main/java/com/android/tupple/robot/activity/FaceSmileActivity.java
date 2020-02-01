package com.android.tupple.robot.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.android.tupple.robot.R;
import com.android.tupple.robot.receiver.RecordingReceiver;
import com.android.tupple.robot.utils.WindowManagerUtils;
import com.android.tupple.trigger.TriggerHelper;
import com.android.tupple.trigger.utils.TriggerConstant;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by tungts on 2020-01-12.
 */

// TODO Calculate battery consumption if always run this activity

public class FaceSmileActivity extends Activity {

    private boolean isStartAsync = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.setFullScreenMode(this);
        setContentView(R.layout.activity_face_smile);
        initGifImage();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        isStartAsync = false;
    }

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        startTrigger();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    private void startTrigger() {
        if (isStartAsync) {
            new Handler().postDelayed(() -> TriggerHelper.startTrigger(this), 200);
        } else {
            TriggerHelper.startTrigger(this);
        }
    }

    private void initGifImage() {
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
        checkPermission();
        registerReceiver(mRecordingReceiver, new IntentFilter(TriggerConstant.ACTION_RECOGNIZE_DONE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mRecordingReceiver);
    }

    RecordingReceiver mRecordingReceiver = new RecordingReceiver();
}
