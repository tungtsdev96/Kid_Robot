package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface EntertainmentView<Fragment> {
    void setTitleHeader(Fragment fragment);
    void setCurrentFragment(Fragment fragment);
    CleanObservable<Fragment> getButtonVideoClickedObservable();
    CleanObservable<Fragment> getButtonAudioClickedObservable();
}
