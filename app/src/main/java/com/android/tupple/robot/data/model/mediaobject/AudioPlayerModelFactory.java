package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

public class AudioPlayerModelFactory {
    public static AudioListModel<Media> newAudioPlayerModelFactory(Context context) {
        return new MediaModelImpl(context);
    }
}
