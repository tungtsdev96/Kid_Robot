package com.android.tupple.robot.domain.presenter.videoyoutubelist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoYoutubeListModel<Media>  {

    CleanObservable<List<Media>> getAllVideoYoutube();
}
