package com.android.tupple.robot.domain.presenter.smartqa;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tungts on 3/22/20.
 */

public interface SmartQAView<QAResponse> {

    void initView();

    void showResult(QAResponse response);

    void showError();

    void onStop();

    void onDestroy();

    CleanObservable getCloseButtonClicked();

}
