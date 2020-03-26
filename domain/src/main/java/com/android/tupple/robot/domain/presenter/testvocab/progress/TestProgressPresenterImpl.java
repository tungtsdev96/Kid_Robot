package com.android.tupple.robot.domain.presenter.testvocab.progress;

import com.android.tupple.robot.domain.entity.testvocab.TestProgressPresenter;
import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.presenter.data.LearningVocabModel;

/**
 * Created by tungts on 2020-03-09.
 */

public class TestProgressPresenterImpl implements TestProgressPresenter {

    private int mTotalVocab;
    private int mTotalRightAnswer;

    private double mTotalScore;
    private double mProgress;

    private TestProgressView mTestProgressView;
    private LearningVocabModel mLearningVocabModel;

    private BtnCloseHandler mBtnCloseHandler;

    public void setLearningVocabModel(LearningVocabModel learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    public interface BtnCloseHandler {
        void onClicked();
    }

    public void setBtnCloseHandler(BtnCloseHandler btnCloseHandler) {
        this.mBtnCloseHandler = btnCloseHandler;
    }

    public void setTestProgressView(TestProgressView testProgressView) {
        this.mTestProgressView = testProgressView;
        mTestProgressView.getBtnCloseClickedObservable().subscribe(this::handleBtnCloseClicked);
    }

    private void handleBtnCloseClicked() {
        if (mBtnCloseHandler != null) {
            mBtnCloseHandler.onClicked();
        }
    }

    private void getTotalVocabulary() {
        int totalVocab = mLearningVocabModel.getListVocabLearning().size();
        setTotalVocabulary(totalVocab);
    }

    private void setTotalVocabulary(int totalVocab) {
        mTotalVocab = totalVocab;
        mTotalScore = 3 * totalVocab;
        mProgress = 1;
    }

    @Override
    public void start() {
        getTotalVocabulary();
    }

    @Override
    public void addRightAnswer(boolean isRight, double progress) {
        mProgress += progress;
        CLog.printD("progress", mProgress + " " + progress);
        mTestProgressView.setProgress((int) (mProgress * 100 / mTotalScore));

        if (isRight) {
            mTotalRightAnswer++;
        }
    }

}
