package com.android.tupple.robot.domain.presenter.learnvocab;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LearningVocabView<Vocabulary> {

    void initLayout();

    void setCurrentVocabLearning(Vocabulary currentVocab);

    CleanObservable getNextButtonClickedObservable();

    CleanObservable getPreviousButtonClickedObservable();
}
