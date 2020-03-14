package com.android.tupple.robot.domain.presenter.videolist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoListView<Media> {
    void setListVideo(List<Media> listMedia);

    CleanObservable<Media> getItemVideoClickedObservable();
}
