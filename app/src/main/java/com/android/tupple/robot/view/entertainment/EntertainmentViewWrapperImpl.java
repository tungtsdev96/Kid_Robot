package com.android.tupple.robot.view.entertainment;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;
import com.android.tupple.robot.view.englishbook.EnglishBookFragment;
import com.android.tupple.robot.view.englishtopic.EnglishTopicFragment;

public class EntertainmentViewWrapperImpl implements EntertainmentViewWrapper {
    private Bundle mBundle;

    private FragmentManager mFragmentManager;
    private EntertainmentFragment mEntertainmentFragment;
    private CleanObserver<EntertainmentView> mViewCreatedObserver;
    EntertainmentViewWrapperImpl(FragmentManager fragmentManager, Bundle bundle) {
        mFragmentManager = fragmentManager;
        this.mBundle = bundle;
    }
    @Override
    public CleanObservable<EntertainmentView> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createEnglishTopicFragment();
        setViewCreatedObserverOnFragment();
    }
    private void createEnglishTopicFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mEntertainmentFragment = (EntertainmentFragment) mFragmentManager.findFragmentByTag(EntertainmentFragment.TAG);
        if (mEntertainmentFragment == null) {
            mEntertainmentFragment = EntertainmentFragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_menu, mEntertainmentFragment, EntertainmentFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mEntertainmentFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }
    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}
