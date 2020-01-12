package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.domain.entity.menumain.MenuMain;
import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private MenuMain mMenuMain;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initFirstBatch(savedInstanceState);
        inject(savedInstanceState);
    }

    private void initFirstBatch(Bundle bundle) {
        mMenuMain = new MenuMain();
        mActivityLauncher = new ActivityLauncher(this);
    }

    private void inject(Bundle bundle) {
        injectDrawer(bundle);
        injectEnglishBook(bundle);
        injectEnglishTopic(bundle);
    }

    private void injectEnglishTopic(Bundle bundle) {

    }

    private void injectEnglishBook(Bundle bundle) {

    }

    private void injectDrawer(Bundle bundle) {

    }

}
