package com.android.tupple.robot.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.music.MultiPlayer;
import com.android.tupple.robot.data.model.smartqa.SmartQAModelFactory;
import com.android.tupple.robot.data.model.smartqa.SmartQAModelImpl;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAModel;
import com.android.tupple.robot.receiver.TriggerReceiver;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.android.tupple.robot.utils.WindowManagerUtils;
import com.android.tupple.robot.view.smartqa.RecordingPopupView;
import com.android.tupple.trigger.TriggerConstant;
import com.android.tupple.trigger.TriggerHelper;
import com.android.tupple.trigger.TriggerService;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by tungts on 2020-01-12.
 */

// TODO Calculate battery consumption if always run this activity

public class FaceSmileActivity extends Activity implements RecordingPopupView.OnRecordingPopupListener {

    private boolean isStartAsync = true;

    private SmartQAModel<QAResponse> smartQAModel;
    private MultiPlayer mMultiPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.setFullScreenMode(this);
        setContentView(R.layout.activity_face_smile);
        smartQAModel = SmartQAModelFactory.newSmartQAModel(this);
        mMultiPlayer = new MultiPlayer(this);
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
            new Handler().postDelayed(() -> TriggerHelper.startTrigger(this), 500);
        } else {
            TriggerHelper.startTrigger(this);
        }
    }

    private void initGifImage() {
        GifImageView mGifImageView = findViewById(R.id.img_gif_face);
        mGifImageView.setImageResource(R.drawable.face_smile);
        SingleClickUtil.registerListener(mGifImageView, v -> {
            startActivity(new Intent(FaceSmileActivity.this, MainActivity.class));
        });
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
        registerReceiver(triggerReceiver, new IntentFilter(TriggerConstant.ACTION_RECOGNIZE_DONE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        TriggerHelper.stopTrigger(this);
        unregisterReceiver(triggerReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMultiPlayer != null && mMultiPlayer.isPlaying()) {
            mMultiPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMultiPlayer != null) {
            mMultiPlayer.destroy();
        }
//        RecognitionPopupView.removeInstance(mActivity.hashCode());
        RecordingPopupView.removeInstance(this.hashCode());
    }

    TriggerReceiver triggerReceiver = new TriggerReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);

            String action = intent.getAction();

            Log.d(TriggerService.TAG, "RecordingReceiver " + action);

            if (TextUtils.isEmpty(action)) {
                return;
            }

            if (TriggerConstant.ACTION_RECOGNIZE_DONE.equals(action)) {
//                startSmartQAActivity(context);
                showPopup();
            }

        }
    };

    private void showPopup() {
        Log.d("RecordingThread", "showPopup");
        RecordingPopupView.getInstance(FaceSmileActivity.this.hashCode())
                .init(FaceSmileActivity.this.getResources())
                .setParentView(FaceSmileActivity.this.findViewById(R.id.img_gif_face))
                .setOnShowCompleteListener(FaceSmileActivity.this)
                .show();
    }

    @Override
    public void onError(int type) {

    }

    @Override
    public void onResultBuffer(short[] data) {
        TriggerHelper.startTrigger(this);
        smartQAModel.getAnswerObservable(data).subscribe(
                qaResponse -> {
                    mMultiPlayer.play(qaResponse.getLinkAudio());
                }, Throwable::printStackTrace);
    }

    @Override
    public void onResultBuffer(String filePath) {
        TriggerHelper.startTrigger(this);
        if (filePath != null) {
            smartQAModel.getAnswerObservable(filePath, null).subscribe(
                    qaResponse -> {
                        mMultiPlayer.play(qaResponse.getLinkAudio());
                    }, Throwable::printStackTrace);
        }
    }
}
