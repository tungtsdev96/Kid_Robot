package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListModel;

public class VideoYoutubeListModelFactory {
    public static VideoYoutubeListModel<Media> newVideoListModel(Context context) {
        return new MediaModelImpl(context);
    }
}
