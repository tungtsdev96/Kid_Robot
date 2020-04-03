package com.android.tupple.robot.domain.presenter.videoyoutubelist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoYoutubeListView<Media> {
    void initLayout();

    void setListVideoYoutube(List<Media> listMedia);

    CleanObservable<Media> getItemVideoYoutubeClickedObservable();

    CleanObservable getCloseButtonClickedObservable();
}
