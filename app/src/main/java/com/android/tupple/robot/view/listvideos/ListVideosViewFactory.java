package com.android.tupple.robot.view.listvideos;

import android.app.Activity;
import android.os.Bundle;

import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.view.listaudio.ListAudioViewImpl;

public class ListVideosViewFactory {
    public static VideoListView newVideoListView(Activity activity, Bundle bundle) {
        return new ListVideosViewImpl(activity, bundle);
        //return null;
    }
}
