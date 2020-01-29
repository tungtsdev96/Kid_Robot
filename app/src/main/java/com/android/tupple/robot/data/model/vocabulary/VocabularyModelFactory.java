package com.android.tupple.robot.data.model.vocabulary;

import android.content.Context;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;

/**
 * Created by tungts on 2020-01-29.
 */

public class VocabularyModelFactory {

    public static VocabularyModel<Vocabulary> newVocabularyModel(Context context) {
        return new VocabularyModelImpl(context);
    }

}
