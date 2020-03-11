package com.android.tupple.robot.domain.presenter.audioplayer;

import com.android.tupple.cleanobject.CleanObservable;

public interface AudioPlayerView<Media> {
    void initLayout();

    void setCurrentAudio(Media media);

    void preparePlayer();

    void playAudio();

    void changeIconStopPlay(boolean isPlay);

    void pauseAudio();

    void stopAudio();

    CleanObservable getCloseButtonClickedObservable();

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();

    CleanObservable getStopPlayButtonClickedObservable();
}
