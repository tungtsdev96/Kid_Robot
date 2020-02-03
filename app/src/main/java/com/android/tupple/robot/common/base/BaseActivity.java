package com.android.tupple.robot.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.tupple.robot.utils.WindowManagerUtils;

/**
 * Created by tungts on 2020-01-12.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        WindowManagerUtils.setFullScreenMode(this);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutContent());
        onCreatedActivity(savedInstanceState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            WindowManagerUtils.setFullScreenMode(this);
        }
    }

    protected abstract int getLayoutContent();

    protected abstract void onCreatedActivity(Bundle savedInstanceState);

}
