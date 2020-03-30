package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface EntertainmentView<Fragment> {
    void initLayout();

    void closeEntertainmentActivity();

    CleanObservable<Fragment> getButtonVideoClickedObservable();

    CleanObservable<Fragment> getButtonAudioClickedObservable();

    CleanObservable getButtonCloseClickedObservable();
}
