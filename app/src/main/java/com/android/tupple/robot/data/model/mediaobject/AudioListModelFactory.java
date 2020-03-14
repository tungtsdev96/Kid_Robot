package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;

public class AudioListModelFactory {
    public static AudioListModel<Media> newAudioListModel(Context context) {
        return new MediaModelImpl(context);
    }
}
