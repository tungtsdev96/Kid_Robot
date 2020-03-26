package com.android.tupple.robot.domain.presenter.testvocab.progress;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tungts on 2020-03-09.
 */

public interface TestProgressView {

    void setProgress(int progress);

    CleanObservable getBtnCloseClickedObservable();

}
