package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;

public class AudioListModelFactory {
    public static AudioListModel<Media> newAudioListModel(Context context) {
        return new MediaModelImpl(context);
    }
}
