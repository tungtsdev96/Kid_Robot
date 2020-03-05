package com.android.tupple.robot.domain.presenter.learnvocab;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LearningVocabView<Vocabulary> {

    void initLayout();

    void setListVocabLearning(List<Vocabulary> listVocabLearning);

    void enablePreviousButton(boolean isEnable);

    void setCurrentSlide(int pos);

    void setTitleHeader(int pos, int total);

    CleanObservable getCloseButtonClickedObservable();

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();
}
