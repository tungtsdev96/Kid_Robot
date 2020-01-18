package com.android.tupple.robot.view.learningvocab.item;

import com.android.tupple.robot.utils.constant.LearnVocabConstant;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearnVocabImage implements LearnVocabItem {

    @Override
    public int getIdVocab() {
        return 0;
    }

    @Override
    public String getSound() {
        return null;
    }

    @Override
    public int getType() {
        return LearnVocabConstant.LEARN_TYPE.IMAGE;
    }

}
