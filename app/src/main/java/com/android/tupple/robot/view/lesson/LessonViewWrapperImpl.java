package com.android.tupple.robot.view.lesson;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.domain.presenter.lesson.LessonView;
import com.android.tupple.robot.domain.presenter.lesson.LessonViewWrapper;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonViewWrapperImpl implements LessonViewWrapper<LessonData> {

    private Bundle mBundle;

    private FragmentManager mFragmentManager;
    private LessonFragment mLessonFragment;
    private CleanObserver<LessonView<LessonData>> mViewCreatedObserver;

    LessonViewWrapperImpl(FragmentManager fragmentManager, Bundle bundle) {
        mFragmentManager = fragmentManager;
        this.mBundle = bundle;
    }

    @Override
    public CleanObservable<LessonView<LessonData>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createLessonFragment();
        setViewCreatedObserverOnFragment();
    }

    private void createLessonFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLessonFragment = (LessonFragment) mFragmentManager.findFragmentByTag(LessonFragment.TAG);
        if (mLessonFragment == null) {
            mLessonFragment = LessonFragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_lesson, mLessonFragment, LessonFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mViewCreatedObserver != null) {
            mLessonFragment.setViewCreatedObserver(mViewCreatedObserver);
        }
    }

    @Override
    public void hide() {
        if (mLessonFragment == null) {

        }

        // TODO hide EnglishBookFragment
    }

    @Override
    public void invalidate() {

    }

}
