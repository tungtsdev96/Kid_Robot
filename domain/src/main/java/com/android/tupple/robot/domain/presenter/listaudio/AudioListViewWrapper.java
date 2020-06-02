package com.android.tupple.robot.domain.presenter.listaudio;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

public interface AudioListViewWrapper<Media> extends IView {
    CleanObservable<AudioListView<Media>> getViewCreatedObservable();
}
