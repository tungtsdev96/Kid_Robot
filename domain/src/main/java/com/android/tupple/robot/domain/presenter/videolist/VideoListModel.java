package com.android.tupple.robot.domain.presenter.videolist;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface VideoListModel<Media> {
    CleanObservable<List<Media>> getAllVideo();
}
