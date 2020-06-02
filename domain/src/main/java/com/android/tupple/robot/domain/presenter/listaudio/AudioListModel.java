package com.android.tupple.robot.domain.presenter.listaudio;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

public interface AudioListModel<Media> extends IModel {


    CleanObservable<List<Media>> getAllAudio();
    CleanObservable updateAudio(Media media);

}

