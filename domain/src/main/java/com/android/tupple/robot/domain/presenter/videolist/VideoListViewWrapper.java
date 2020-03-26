package com.android.tupple.robot.domain.presenter.videolist;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

public interface VideoListViewWrapper<Media> extends IView {
    CleanObservable<VideoListView<Media>> getViewCreatedObservable();
}
