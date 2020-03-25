package com.android.tupple.robot.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.tupple.robot.R;
import com.android.tupple.robot.data.model.smartqa.SmartQAModelFactory;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.entity.smartqa.SmartQA;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAModel;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAPresenterImpl;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAView;
import com.android.tupple.robot.utils.WindowManagerUtils;
import com.android.tupple.robot.view.smartqa.SmartQAViewFactory;
import com.android.tupple.trigger.TriggerHelper;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAActivity extends Activity {

    private SmartQA mSmartQA;
    private String mQuestion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.setFullScreenMode(this);
        setContentView(R.layout.activity_smart_qa);
        TriggerHelper.stopTrigger(this);
        mSmartQA = new SmartQA();
        mQuestion = getIntent().getStringExtra("question");
        inject();
        mSmartQA.init();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            WindowManagerUtils.setFullScreenMode(this);
        }
    }

    private void inject() {
        boolean isNeedShowSpeedToText = true;
        SmartQAPresenterImpl<QAResponse> smartQAPresenter = new SmartQAPresenterImpl<>();
        SmartQAView<QAResponse> smartQAView = SmartQAViewFactory.newSmartQAView(this);
        SmartQAModel<QAResponse> smartQAModel = SmartQAModelFactory.newSmartQAModel(this);

        smartQAPresenter.setQAView(smartQAView);
        smartQAPresenter.setQAModel(smartQAModel);
        smartQAPresenter.setNeedShowPopUp(isNeedShowSpeedToText);
        smartQAPresenter.setCloseButtonHandler(this::onBackPressed);
        mSmartQA.setQAPresenter(smartQAPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSmartQA.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TriggerHelper.startTrigger(this);
    }
}