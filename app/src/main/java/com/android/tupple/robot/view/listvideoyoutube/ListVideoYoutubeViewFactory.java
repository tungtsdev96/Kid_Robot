package com.android.tupple.robot.view.listvideoyoutube;

import android.app.Activity;
import android.os.Bundle;

import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListView;
import com.android.tupple.robot.view.listvideos.ListVideosViewImpl;

public class ListVideoYoutubeViewFactory {
    public static VideoYoutubeListView newVideoYoutubeListView(Activity activity, Bundle bundle) {
        return new ListVideoYoutubeViewImpl(activity, bundle);
        //return null;
    }
}
