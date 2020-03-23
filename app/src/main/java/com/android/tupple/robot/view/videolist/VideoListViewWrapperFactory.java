package com.android.tupple.robot.view.videolist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;

public class VideoListViewWrapperFactory {
    public static VideoListViewWrapper newVideoListViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new VideoListViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
