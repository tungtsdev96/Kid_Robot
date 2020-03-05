package com.android.tupple.robot.view.testvocab.level2;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2View;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2ViewWrapper;
import com.android.tupple.robot.view.testvocab.level1.Level1Fragment;

/**
 * Created by tungts on 2020-02-11.
 */

public class Level2ViewWrapperImpl implements Level2ViewWrapper<LessonData, Topic, Vocabulary> {

    private FragmentManager mFragmentManager;
    private Level2Fragment mLevel2Fragment;

    private CleanObserver<Level2View<LessonData, Topic, Vocabulary>> mOnViewCreatedObserver;

    public Level2ViewWrapperImpl(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public CleanObservable<Level2View<LessonData, Topic, Vocabulary>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mOnViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createLevel2Fragment();
        setViewCreatedObserverOnFragment();
    }

    private void createLevel2Fragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLevel2Fragment = (Level2Fragment) mFragmentManager.findFragmentByTag(Level2Fragment.TAG);
        if (mLevel2Fragment == null) {
            mLevel2Fragment = Level2Fragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_test_vocab, mLevel2Fragment, Level1Fragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mOnViewCreatedObserver != null) {
            mLevel2Fragment.setViewCreatedObserver(mOnViewCreatedObserver);
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}
