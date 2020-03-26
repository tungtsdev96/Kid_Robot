package com.android.tupple.robot.view.learningvocab;

import android.app.Activity;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.viewpager2.widget.ViewPager2;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabViewImpl implements LearningVocabView<Vocabulary> {

    private Activity mActivity;

    private ViewPager2 mViewPagerVocab;
    private LearningVocabAdapter mLearningVocabAdapter;

    private TextView mTitleLearningVocab;
    private FloatingActionButton btnClose;
    private FloatingActionButton btnNext;
    private FloatingActionButton btnPrevious;

    private CleanObserver mCloseButtonClickedObserver;
    private CleanObserver mNextButtonClickedObserver;
    private CleanObserver mPreviousButtonClickedObserver;

    public LearningVocabViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initLayout() {
        initViewPager();

        mTitleLearningVocab = mActivity.findViewById(R.id.title_learning_vocab);

        btnClose = mActivity.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            if (mCloseButtonClickedObserver != null) {
                mCloseButtonClickedObserver.onNext();
            }
        });
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initViewPager() {
        mViewPagerVocab = mActivity.findViewById(R.id.slide_learning_vocab);
        mViewPagerVocab.setPageTransformer(new DepthPageTransformer());
        mViewPagerVocab.setUserInputEnabled(false);
        mLearningVocabAdapter = new LearningVocabAdapter(mActivity);
        mViewPagerVocab.setAdapter(mLearningVocabAdapter);
    }

    @Override
    public void setListVocabLearning(List<Vocabulary> listVocabLearning) {
        mLearningVocabAdapter.setListVocabLearning(listVocabLearning);
    }

    @Override
    public void enablePreviousButton(boolean isEnable) {
        btnPrevious.setEnabled(isEnable);
    }

    @Override
    public void setCurrentSlide(int pos) {
        if (pos < 0) {
            return;
        }
        mViewPagerVocab.setCurrentItem(pos, true);
    }

    @Override
    public void setTitleHeader(int pos, int total) {
        mTitleLearningVocab.setText(String.format(
                mActivity.getResources().getString(R.string.learning_vocab_title), pos, total
        ));
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mCloseButtonClickedObserver = cleanObserver);
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
