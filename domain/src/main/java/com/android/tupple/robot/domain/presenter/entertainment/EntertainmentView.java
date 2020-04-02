package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

public interface EntertainmentView<Fragment> {
    void initLayout();

    void closeEntertainmentActivity();

    CleanObservable<Fragment> getButtonVideoClickedObservable();

    CleanObservable<Fragment> getButtonAudioClickedObservable();

    CleanObservable<Fragment> getButtonVideoYoutubeClickedObservable();

    CleanObservable getButtonCloseClickedObservable();
}
