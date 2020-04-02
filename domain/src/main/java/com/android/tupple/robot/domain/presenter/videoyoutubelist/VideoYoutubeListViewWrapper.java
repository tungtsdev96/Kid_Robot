package com.android.tupple.robot.domain.presenter.videoyoutubelist;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

public interface VideoYoutubeListViewWrapper<Media> extends IView {
    CleanObservable<VideoYoutubeListView<Media>> getViewCreatedObservable();
}
