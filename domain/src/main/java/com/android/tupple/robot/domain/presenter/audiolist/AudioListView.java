package com.android.tupple.robot.domain.presenter.audiolist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface AudioListView<Media> {
    void setListAudio(List<Media> listMedia);

    CleanObservable<Media> getItemAudioClickedObservable();

}
