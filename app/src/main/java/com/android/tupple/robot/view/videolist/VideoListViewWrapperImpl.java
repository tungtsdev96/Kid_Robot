package com.android.tupple.robot.view.videolist;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.entertainment.EntertainmentFragment;

public class VideoListViewWrapperImpl implements VideoListViewWrapper {
    private Bundle bundle;
    private FragmentManager mFragmentManager;
    private VideoListFragment mVideoListFragment;
    private CleanObserver<VideoListView<Media>> mViewCreatedObserver;

    public VideoListViewWrapperImpl(FragmentManager mFragmentManager,Bundle bundle) {
        this.bundle = bundle;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public CleanObservable<VideoListView<Media>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createVideoListFragment();
        setViewCreatedObserverOnFragment();
    }
    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mVideoListFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }
    private void createVideoListFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mVideoListFragment = (VideoListFragment) mFragmentManager.findFragmentByTag(mVideoListFragment.TAG);
        //if (mVideoListFragment == null) {
            mVideoListFragment = VideoListFragment.newInstance();
        //}

        fragmentTransaction.replace(R.id.content_entertainment, mVideoListFragment, mVideoListFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}
