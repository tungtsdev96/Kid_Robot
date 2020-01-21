package com.android.tupple.robot.view.learningvocab;

import android.app.Activity;

import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.common.data.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;

/**
 * Created by tungts on 2020-01-20.
 */

public class LearningVocabViewFactory {

    public static LearningVocabView<LessonData, Vocabulary> newLearningVocabView(Activity activity) {
        return new LearningVocabViewImpl(activity);
    }

}
