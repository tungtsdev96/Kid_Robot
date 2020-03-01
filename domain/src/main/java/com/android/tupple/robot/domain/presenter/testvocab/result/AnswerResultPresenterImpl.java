package com.android.tupple.robot.domain.presenter.testvocab.result;

import com.android.tupple.robot.domain.entity.testvocab.AnswerResultPresenter;

/**
 * Created by tungts on 2020-03-01.
 */

public class AnswerResultPresenterImpl implements AnswerResultPresenter {

    private AnswerResultView mAnswerResultView;

    private BtnContinueHandler mOnBtnContinueHandler;

    public AnswerResultPresenterImpl(){
    }

    public interface BtnContinueHandler{
        void onClick();
    }

    public void setAnswerResultView(AnswerResultView answerResultView){
        mAnswerResultView = answerResultView;
        mAnswerResultView.getBtnContinueClickedObservable().subscribe(this::handleBtnContinueClicked);
    }

    private void handleBtnContinueClicked() {
        hide();
        if (mOnBtnContinueHandler != null) {
            mOnBtnContinueHandler.onClick();
        }
    }

    @Override
    public void showResult(boolean isResult, String result) {
        mAnswerResultView.showResult(isResult);
        mAnswerResultView.setTextResult(result);

    }

    @Override
    public void hide() {
        mAnswerResultView.hide();
    }

    @Override
    public void setOnBtnContinueHandler(BtnContinueHandler btnContinueHandler) {
        mOnBtnContinueHandler = btnContinueHandler;
    }
}
