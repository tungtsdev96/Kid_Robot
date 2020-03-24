package com.android.tupple.robot.domain.presenter.smartqa;

import com.android.tupple.robot.domain.entity.smartqa.SmartQAPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAPresenterImpl<QAResponse> implements SmartQAPresenter {

    private SmartQAView<QAResponse> mQAView;
    private SmartQAModel<QAResponse> mQAModel;
    private String mQuestion;

    private CloseButtonHandler mOnCloseButtonHandler;

    public SmartQAPresenterImpl() {}

    public void setQAModel(SmartQAModel<QAResponse> qAModel) {
        this.mQAModel = qAModel;
    }

    public void setQAView(SmartQAView<QAResponse> qAView) {
        this.mQAView = qAView;
        initObservers();
    }

    public void setQuestion(String question) {
        this.mQuestion = question;
    }

    public void setCloseButtonHandler(CloseButtonHandler closeButtonHandler) {
        this.mOnCloseButtonHandler = closeButtonHandler;
    }

    private void initObservers() {
        mQAView.getCloseButtonClicked().subscribe(() -> {
            if (mOnCloseButtonHandler != null) {
                mOnCloseButtonHandler.onClose();
            }
        });
    }

    @Override
    public void init() {
        mQAView.initView();
        start();
    }

    @Override
    public void start() {
        if (mQuestion == null || mQuestion.isEmpty()) {
            mQAView.showError();
            return;
        }

        mQAModel.getAnswer(mQuestion).subscribe(mQAView::showResult, e -> mQAView.showError());
    }

    @Override
    public void stop() {
        mQAView.onStop();
        mQAModel.stop();
    }

    @Override
    public void destroy() {
        mQAView.onDestroy();
        mQAModel.destroy();
    }
}
