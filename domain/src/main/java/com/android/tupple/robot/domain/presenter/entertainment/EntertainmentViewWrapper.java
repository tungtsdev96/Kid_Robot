package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

public interface EntertainmentViewWrapper<Fragment> extends IView {
    CleanObservable<EntertainmentView> getViewCreatedObservable();
}
