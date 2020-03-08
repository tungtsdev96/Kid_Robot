package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

public interface EntertainmentModel<Media> {
    CleanObservable<List<Media>> getAllVideo();
    CleanObservable<List<Media>> getAllAudio();
}
