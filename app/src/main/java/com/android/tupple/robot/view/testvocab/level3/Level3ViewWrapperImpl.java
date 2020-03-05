package com.android.tupple.robot.view.testvocab.level3;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3View;
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3ViewWrapper;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3ViewWrapperImpl implements Level3ViewWrapper<LessonData, Topic, Vocabulary> {

    private FragmentManager mFragmentManager;
    private Level3Fragment mLevel3Fragment;

    private CleanObserver<Level3View<LessonData, Topic, Vocabulary>> mOnViewCreatedObserver;

    public Level3ViewWrapperImpl(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public CleanObservable<Level3View<LessonData, Topic, Vocabulary>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mOnViewCreatedObserver = cleanObserver);
    }

    @Override
    public void show() {
        createLevel3Fragment();
        setViewCreatedObserverOnFragment();
    }

    private void createLevel3Fragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mLevel3Fragment = (Level3Fragment) mFragmentManager.findFragmentByTag(Level3Fragment.TAG);
        if (mLevel3Fragment == null) {
            mLevel3Fragment = Level3Fragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_test_vocab, mLevel3Fragment, Level3Fragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setViewCreatedObserverOnFragment() {
        if (mOnViewCreatedObserver != null) {
            mLevel3Fragment.setViewCreatedObserver(mOnViewCreatedObserver);
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }

}
