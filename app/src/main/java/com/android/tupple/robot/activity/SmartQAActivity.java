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
        setContentView(R.layout.activity_result_question_answer);
        TriggerHelper.stopTrigger(this);
        findViewById(R.id.btn_close).setOnClickListener(v-> {
            onBackPressed();
        });

        mSmartQA = new SmartQA();
        mQuestion = getIntent().getStringExtra("question");
        inject();
        mSmartQA.init();
    }

    private void inject() {
        SmartQAPresenterImpl<QAResponse> smartQAPresenter = new SmartQAPresenterImpl<>();
        SmartQAView<QAResponse> smartQAView = SmartQAViewFactory.newSmartQAView(this);
        SmartQAModel<QAResponse> smartQAModel = SmartQAModelFactory.newSmartQAModel(this);

        smartQAPresenter.setQAView(smartQAView);
        smartQAPresenter.setQAModel(smartQAModel);
        smartQAPresenter.setQuestion(mQuestion);
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