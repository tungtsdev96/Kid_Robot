package com.android.tupple.robot.view.audiolist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.audiolist.AudioListViewWrapper;

public class AudioListViewWrapperFactory {
    public static AudioListViewWrapper newAudioListViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new AudioListViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
