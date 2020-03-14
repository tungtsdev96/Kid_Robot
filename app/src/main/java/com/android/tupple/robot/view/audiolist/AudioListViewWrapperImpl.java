package com.android.tupple.robot.view.audiolist;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListView;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListViewWrapper;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.view.videolist.VideoListFragment;

public class AudioListViewWrapperImpl implements AudioListViewWrapper {
    private Bundle bundle;
    private FragmentManager mFragmentManager;
    private AudioListFragment mAudioListFragment;
    private CleanObserver<AudioListView<Media>> mViewCreatedObserver;
    public AudioListViewWrapperImpl(FragmentManager mFragmentManager,Bundle bundle) {
        this.bundle = bundle;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public CleanObservable<AudioListView<Media>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createVideoListFragment();
        setViewCreatedObserverOnFragment();
    }
    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mAudioListFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }
    private void createVideoListFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mAudioListFragment = (AudioListFragment) mFragmentManager.findFragmentByTag(mAudioListFragment.TAG);
        //if (mVideoListFragment == null) {
        mAudioListFragment = AudioListFragment.newInstance();
        //}

        fragmentTransaction.replace(R.id.content_entertainment, mAudioListFragment, mAudioListFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}
