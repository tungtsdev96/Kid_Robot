package com.android.tupple.robot.view.learningvocab;

import android.app.Activity;

import androidx.viewpager2.widget.ViewPager2;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.common.data.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabViewImpl implements LearningVocabView<LessonData, Vocabulary> {

    private Activity mActivity;

    private ViewPager2 mViewPagerVocab;
    private LearningVocabAdapter mLearningVocabAdapter;

    public LearningVocabViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void initLayout() {
        initViewPager();
    }

    private void initViewPager() {
        mViewPagerVocab = mActivity.findViewById(R.id.slide_learning_vocab);
        mLearningVocabAdapter = new LearningVocabAdapter(mActivity);
        mViewPagerVocab.setAdapter(mLearningVocabAdapter);
    }
}
