package com.android.tupple.robot.view.videoyoutubelist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListViewWrapper;

public class VideoYoutubeListViewWrapperFactory {
    public static VideoYoutubeListViewWrapper newVideoYoutubeListViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new VideoYoutubeListViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
