package com.android.tupple.robot.view.learningvocab;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabViewImpl implements LearningVocabView<Vocabulary> {

    private Activity mActivity;

    private ViewPager2 mViewPagerVocab;
    private LearningVocabAdapter mLearningVocabAdapter;

    private TextView btnNext;
    private TextView btnPrevious;

    private CleanObserver mNextButtonClickedObserver;
    private CleanObserver mPreviousButtonClickedObserver;

    public LearningVocabViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void initLayout() {
        initViewPager();
        btnNext = mActivity.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(v -> {
            if (mNextButtonClickedObserver != null) {
                mNextButtonClickedObserver.onNext();
            }
        });
        btnPrevious = mActivity.findViewById(R.id.btn_previous);
        btnPrevious.setOnClickListener(v -> {
            if (mPreviousButtonClickedObserver != null) {
                mPreviousButtonClickedObserver.onNext();
            }
        });
    }

    private void initViewPager() {
        mViewPagerVocab = mActivity.findViewById(R.id.slide_learning_vocab);
        mLearningVocabAdapter = new LearningVocabAdapter(mActivity);
        mViewPagerVocab.setAdapter(mLearningVocabAdapter);
        mViewPagerVocab.registerOnPageChangeCallback(mOnPageChangeCallback);
    }

    private ViewPager2.OnPageChangeCallback mOnPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    public void setCurrentVocabLearning(Vocabulary currentVocab) {
        mLearningVocabAdapter.setCurrentVocabLearning(currentVocab);
        mViewPagerVocab.setCurrentItem(0,false);
    }

    @Override
    public CleanObservable getNextButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mNextButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getPreviousButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mPreviousButtonClickedObserver = cleanObserver);
    }
}
