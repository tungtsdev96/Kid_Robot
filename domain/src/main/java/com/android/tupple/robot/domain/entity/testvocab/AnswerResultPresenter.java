package com.android.tupple.robot.domain.entity.testvocab;

import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultPresenterImpl;

/**
 * Created by tungts on 2020-03-01.
 */

public interface AnswerResultPresenter {

    void showResult(boolean isResult, String result);

    void hide();

    void setOnBtnContinueHandler(AnswerResultPresenterImpl.BtnContinueHandler btnContinueHandler);
}
