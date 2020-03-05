package com.android.tupple.robot.view.testvocab.level1;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1View;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1ViewWrapper;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1ViewWrapperImpl implements Level1ViewWrapper<LessonData, Topic, Vocabulary> {

    private FragmentManager mFragmentManager;
    private Level1Fragment mLevel1Fragment;

    private CleanObserver<Level1View<LessonData, Topic, Vocabulary>> mOnViewCreatedObserver;

    public Level1ViewWrapperImpl(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public CleanObservable<Level1View<LessonData, Topic, Vocabulary>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mOnViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createLevel1Fragment();
        setViewCreatedObserverOnFragment();
    }

    private void createLevel1Fragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLevel1Fragment = (Level1Fragment) mFragmentManager.findFragmentByTag(Level1Fragment.TAG);
        if (mLevel1Fragment == null) {
            mLevel1Fragment = Level1Fragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_test_vocab, mLevel1Fragment, Level1Fragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mOnViewCreatedObserver != null) {
            mLevel1Fragment.setViewCreatedObserver(mOnViewCreatedObserver);
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }

}
