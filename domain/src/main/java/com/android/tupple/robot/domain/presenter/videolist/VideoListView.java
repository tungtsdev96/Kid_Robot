package com.android.tupple.robot.domain.presenter.videolist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoListView<Media> {
    void initLayout();

    void setListVideo(List<Media> listMedia);

    void showDialogDescription(Media media);

    CleanObservable<Media> getItemVideoClickedObservable();

    CleanObservable getCloseButtonClickedObservable();
}
