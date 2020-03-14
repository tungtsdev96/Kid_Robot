package com.android.tupple.robot.view.videolist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.entertainment.EntertainmentViewWrapperImpl;

public class VideoListViewWrapperFactory {
    public static VideoListViewWrapper newVideoListViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new VideoListViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
