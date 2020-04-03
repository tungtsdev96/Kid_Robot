package com.android.tupple.robot.domain.presenter.listaudio;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface AudioListView<Media> {
    void initLayout();

    void setListAudio(List<Media> listMedia);

    void setCurrentAudio(Media media);

    void preparePlayer();

    void playAudio();

    void changeIconStopPlay(boolean isPlay);

    void pauseAudio();

    void stopAudio();

    void scrollToItem(int position);

    void stopPlayer();

    CleanObservable<Media> getItemAudioClickedObservable();

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();

    CleanObservable getStopPlayButtonClickedObservable();

    CleanObservable getCloseButtonClickedObservable();
}
