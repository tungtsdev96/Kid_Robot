package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

public interface EntertainmentView {
    void initLayout();

    CleanObservable getButtonVideoClickedObservable();

    CleanObservable getButtonAudioClickedObservable();

    CleanObservable getButtonCloseClickedObservable();
}
