package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;
import com.android.tupple.robot.domain.entity.learnvocab.LearnVocab;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabActivity extends BaseActivity {

    private ActivityLauncher mActivityLauncher;
    private LearnVocab mLearnVocab;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_learning_vocab;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initFirstBatch();
        inject();
    }

    private void initFirstBatch() {
        mActivityLauncher = new ActivityLauncher(this);
        mLearnVocab = new LearnVocab();
    }

    private void inject() {

    }

}
