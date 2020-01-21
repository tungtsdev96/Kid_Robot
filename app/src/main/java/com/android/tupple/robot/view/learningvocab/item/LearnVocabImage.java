package com.android.tupple.robot.view.learningvocab.item;

import com.android.tupple.robot.common.data.Vocabulary;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearnVocabImage extends LearnVocabItem {

    public LearnVocabImage(Vocabulary vocabulary) {
        super(vocabulary);
    }

    @Override
    public int getType() {
        return LearnVocabConstant.LEARN_TYPE.IMAGE;
    }

}
