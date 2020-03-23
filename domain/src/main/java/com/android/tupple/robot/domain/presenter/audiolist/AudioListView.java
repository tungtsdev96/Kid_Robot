package com.android.tupple.robot.domain.presenter.audiolist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface AudioListView<Media> {
    void setListAudio(List<Media> listMedia);

    void setCurrentAudio(Media media);

    void preparePlayer();

    void playAudio();

    void changeIconStopPlay(boolean isPlay);

    void pauseAudio();

    void stopAudio();

    void scrollToItem(int position);

    CleanObservable<Media> getItemAudioClickedObservable();

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();

    CleanObservable getStopPlayButtonClickedObservable();

}
