package com.android.tupple.robot.view.audiolist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.audiolist.AudioListViewWrapper;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.videolist.VideoListViewWrapperImpl;

public class AudioListViewWrapperFactory {
    public static AudioListViewWrapper newAudioListViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new AudioListViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
