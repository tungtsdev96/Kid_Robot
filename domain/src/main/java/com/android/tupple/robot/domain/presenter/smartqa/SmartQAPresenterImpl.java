package com.android.tupple.robot.domain.presenter.smartqa;

import com.android.tupple.robot.domain.entity.smartqa.SmartQAPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAPresenterImpl<QAResponse> implements SmartQAPresenter {

    private SmartQAView<QAResponse> mQAView;
    private SmartQAModel<QAResponse> mQAModel;
    private boolean mIsNeedShowSpeedToTextPopup;

    private CloseButtonHandler mOnCloseButtonHandler;

    public SmartQAPresenterImpl() {}

    public void setNeedShowPopUp(boolean isNeedShowSpeedToTextPopup) {
        this.mIsNeedShowSpeedToTextPopup = isNeedShowSpeedToTextPopup;
    }

    public void setQAModel(SmartQAModel<QAResponse> qAModel) {
        this.mQAModel = qAModel;
    }

    public void setQAView(SmartQAView<QAResponse> qAView) {
        this.mQAView = qAView;
        initObservers();
    }

    public void setCloseButtonHandler(CloseButtonHandler closeButtonHandler) {
        this.mOnCloseButtonHandler = closeButtonHandler;
    }

    private void initObservers() {
        mQAView.getCloseButtonClickedObservable().subscribe(() -> {
            if (mOnCloseButtonHandler != null) {
                mOnCloseButtonHandler.onClose();
            }
        });
        mQAView.getAskRobotButtonClickedObservable().subscribe(() -> mQAView.showPopUpSpeedToText());
        mQAView.getResultSpeedToTextObservable().subscribe(this::handleQuestionFromUser);
        mQAView.getResultBufferAudioObservable().subscribe(this::handleAudioFromUser);
    }

    private void handleAudioFromUser(String filePath) {
        mQAModel.getAnswerObservable(filePath, null).subscribe(mQAView::addResultFromRobot, (e) -> mQAView.showError());
    }

    private void handleQuestionFromUser(String question) {
        mQAModel.getAnswerObservable(question).subscribe(mQAView::addResultFromRobot, (e) -> mQAView.showError());
    }

    @Override
    public void init() {
        mQAView.initView();
        start();
    }

    @Override
    public void start() {
        if (!mIsNeedShowSpeedToTextPopup) {
            mQAView.updateFromRobot(0);
        }

        if (mIsNeedShowSpeedToTextPopup) {
            mQAView.showPopUpSpeedToText();
        }
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
