package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.domain.entity.testvocab.TestVocab;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class TestVocabActivity extends BaseActivity {

    private TestVocab mTestVocab;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.fragment_test_vocab_level_1;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        mTestVocab = new TestVocab();
        mActivityLauncher = new ActivityLauncher(this);

        Bundle bundle = getBundleFromIntent();
    }

    private Bundle getBundleFromIntent() {
        Intent intent = getIntent();
        return intent.getBundleExtra("");
    }

}
