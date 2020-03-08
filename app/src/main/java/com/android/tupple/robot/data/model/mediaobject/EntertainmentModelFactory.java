package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

public class EntertainmentModelFactory {

    public static EntertainmentModel<Media> newEntertainmentModel(Context context) {
        return new VideoModelImpl(context);
    }
}
