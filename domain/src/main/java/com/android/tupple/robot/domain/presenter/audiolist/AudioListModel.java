package com.android.tupple.robot.domain.presenter.audiolist;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

public interface AudioListModel<Media> extends IModel {

    CleanObservable<List<Media>> getAllAudio();


}

