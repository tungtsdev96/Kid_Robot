package com.android.tupple.robot.view.testvocab.level1.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerTextAndImageItem implements AnswerItem {

    private boolean isAnswer;
    private Vocabulary vocabulary;

    private AnswerTextAndImageItem(Vocabulary vocabulary, boolean isAnswer) {
        this.vocabulary = vocabulary;
        this.isAnswer = isAnswer;
    }

    public static AnswerTextAndImageItem create(Vocabulary vocabulary, boolean isAnswer) {
        return new AnswerTextAndImageItem(vocabulary, isAnswer);
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

}
