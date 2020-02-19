package com.android.tupple.robot.data.model.learningvocab;

import android.content.Context;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;

/**
 * Created by tungts on 2020-02-16.
 */

public class LearningVocabModelFactory {

    public static LearningVocabModel<Vocabulary> newLearningVocabModel(Context context) {
        return LearningVocabModelImpl.newInstance(context);
    }

}
