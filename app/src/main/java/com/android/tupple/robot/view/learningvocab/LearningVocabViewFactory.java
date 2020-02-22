package com.android.tupple.robot.view.learningvocab;

import android.app.Activity;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;

/**
 * Created by tungts on 2020-01-20.
 */

public class LearningVocabViewFactory {

    public static LearningVocabView<Vocabulary> newLearningVocabView(Activity activity) {
        return new LearningVocabViewImpl(activity);
    }

}
