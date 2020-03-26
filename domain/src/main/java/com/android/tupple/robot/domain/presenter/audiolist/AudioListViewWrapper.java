package com.android.tupple.robot.domain.presenter.audiolist;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;

public interface AudioListViewWrapper<Media> extends IView {
    CleanObservable<AudioListView<Media>> getViewCreatedObservable();
}
