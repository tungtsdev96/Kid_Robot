package com.android.tupple.robot.domain.presenter.audioplayer;

import com.android.tupple.cleanobject.CleanObservable;

public interface AudioPlayerView<Media> {
    void initLayout();

    void getListAudio();

    void preparePlayer(Media media);

    void playCurrentAudio(Media media);

    CleanObservable getCloseButtonClickedObservable();

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();
}
