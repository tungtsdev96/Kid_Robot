package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

public class EntertainmentModelFactory {

    public static EntertainmentModel newEntertainmentModel(Context context) {
        return new EntertainmentModelImpl(context);
    }
}
