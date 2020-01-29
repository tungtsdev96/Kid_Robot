package com.android.tupple.robot.view.englishtopic;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;
import com.android.tupple.robot.view.englishbook.EnglishBookFragment;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicViewWrapperImpl implements EnglishTopicViewWrapper<Topic> {

    private Bundle mBundle;

    private FragmentManager mFragmentManager;
    private EnglishTopicFragment mEnglishTopicFragment;
    private CleanObserver<EnglishTopicView<Topic>> mViewCreatedObserver;

    EnglishTopicViewWrapperImpl(FragmentManager fragmentManager, Bundle bundle) {
        mFragmentManager = fragmentManager;
        this.mBundle = bundle;
    }

    @Override
    public CleanObservable<EnglishTopicView<Topic>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createEnglishTopicFragment();
        setViewCreatedObserverOnFragment();
    }

    private void createEnglishTopicFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mEnglishTopicFragment = (EnglishTopicFragment) mFragmentManager.findFragmentByTag(EnglishTopicFragment.TAG);
        if (mEnglishTopicFragment == null) {
            mEnglishTopicFragment = EnglishTopicFragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_menu, mEnglishTopicFragment, EnglishBookFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mEnglishTopicFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }

    @Override
    public void hide() {
        if (mEnglishTopicFragment == null) {

        }

        // TODO hide EnglishBookFragment
    }

    @Override
    public void invalidate() {

    }

}
