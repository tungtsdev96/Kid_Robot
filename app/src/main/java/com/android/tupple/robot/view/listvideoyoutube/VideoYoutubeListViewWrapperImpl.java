package com.android.tupple.robot.view.listvideoyoutube;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListView;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListViewWrapper;


public class VideoYoutubeListViewWrapperImpl implements VideoYoutubeListViewWrapper {
    private Bundle bundle;
    private FragmentManager mFragmentManager;
    private VideoYoutubeListFragment mVideoYoutubeListFragment;
    private CleanObserver<VideoYoutubeListView<Media>> mViewCreatedObserver;

    public VideoYoutubeListViewWrapperImpl(FragmentManager mFragmentManager, Bundle bundle) {
        this.bundle = bundle;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public CleanObservable<VideoYoutubeListView<Media>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createVideoYoutubeListFragment();
        setViewCreatedObserverOnFragment();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mVideoYoutubeListFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }

    private void createVideoYoutubeListFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mVideoYoutubeListFragment = VideoYoutubeListFragment.newInstance();
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}

