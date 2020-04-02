package com.android.tupple.robot.domain.presenter.videoyoutubelist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoYoutubeListView<Media>{
    void setListVideoYoutube(List<Media> listMedia);


    CleanObservable<Media> getItemVideoYoutubeClickedObservable();
}