package com.android.tupple.robot.view.learningvocab.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearnVocabTextAndImage extends LearnVocabItem {

    public LearnVocabTextAndImage(Vocabulary vocabulary) {
        super(vocabulary);
    }

    public int getType() {
        return LearnVocabConstant.LEARN_TYPE.TEXT_AND_IMAGE;
    }

}
