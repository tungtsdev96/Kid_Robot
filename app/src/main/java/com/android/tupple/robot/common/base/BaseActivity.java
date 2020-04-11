package com.android.tupple.robot.common.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.tupple.robot.KidRobotApplication;
import com.android.tupple.robot.utils.WindowManagerUtils;

/**
 * Created by tungts on 2020-01-12.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private KidRobotApplication kidRobotApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        kidRobotApplication = (KidRobotApplication)this.getApplicationContext();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        kidRobotApplication.setCurrentActivity(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }
    private void clearReferences(){
        Activity currActivity = kidRobotApplication.getCurrentActivity();
        if (this.equals(currActivity))
            kidRobotApplication.setCurrentActivity(null);
    }
}
