package com.android.tupple.robot.view.englishbook;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookView;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookViewWrapper;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookViewWrapperImpl implements EnglishBookViewWrapper<SchoolBook> {

    private Bundle mBundle;

    private FragmentManager mFragmentManager;
    private EnglishBookFragment mEnglishBookFragment;
    private CleanObserver<EnglishBookView<SchoolBook>> mViewCreatedObserver;

    EnglishBookViewWrapperImpl(FragmentManager fragmentManager, Bundle bundle) {
        mFragmentManager = fragmentManager;
        this.mBundle = bundle;
    }

    @Override
    public CleanObservable<EnglishBookView<SchoolBook>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createEnglishBookFragment();
        setViewCreatedObserverOnFragment();
    }

    private void createEnglishBookFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mEnglishBookFragment = (EnglishBookFragment) mFragmentManager.findFragmentByTag(EnglishBookFragment.TAG);
        if (mEnglishBookFragment == null) {
            mEnglishBookFragment = EnglishBookFragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_menu, mEnglishBookFragment, EnglishBookFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mEnglishBookFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }

    @Override
    public void hide() {
        if (mEnglishBookFragment == null) {

        }

        // TODO hide EnglishBookFragment
    }

    @Override
    public void invalidate() {

    }

}
