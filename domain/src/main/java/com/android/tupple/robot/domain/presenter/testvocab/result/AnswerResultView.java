package com.android.tupple.robot.domain.presenter.testvocab.result;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-03-01.
 */

public interface AnswerResultView extends IView {

    void showResult(boolean isResult);

    void setTextResult(String result);

    CleanObservable getBtnContinueClickedObservable();
}
