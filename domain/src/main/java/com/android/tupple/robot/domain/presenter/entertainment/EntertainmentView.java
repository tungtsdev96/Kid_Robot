package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface EntertainmentView<Media> {
    void setListVideo(List<Media> listVideo);
    void setListAudio(List<Media> listAudio);
    CleanObservable<Media> getItemVideoClickedObservable();
    CleanObservable<Media> getItemAudioClickedObservable();
}
