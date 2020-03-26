package com.android.tupple.robot.view.entertainment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;
import com.android.tupple.robot.view.lesson.LessonFragment;

public class EntertainmentViewWrapperImpl implements EntertainmentViewWrapper {
    private Bundle mBundle;

    private static FragmentManager mFragmentManager;
    private EntertainmentFragment mEntertainmentFragment;
    private CleanObserver<EntertainmentView<Fragment>> mViewCreatedObserver;
    EntertainmentViewWrapperImpl(FragmentManager fragmentManager, Bundle bundle) {
        mFragmentManager = fragmentManager;
        this.mBundle = bundle;
    }
    @Override
    public CleanObservable<EntertainmentView<Fragment>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createEntertainmentFragment();
        setViewCreatedObserverOnFragment();
    }
    private void createEntertainmentFragment() {
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
