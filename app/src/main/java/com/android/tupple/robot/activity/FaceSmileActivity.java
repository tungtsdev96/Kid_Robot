package com.android.tupple.robot.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.WindowManagerUtils;

import pl.droidsonroids.gif.GifImageView;

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
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
