package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

public interface EntertainmentModel<Media> extends IModel {
    CleanObservable<List<Media>> getAllVideo();
    CleanObservable<List<Media>> getAllAudio();
}
