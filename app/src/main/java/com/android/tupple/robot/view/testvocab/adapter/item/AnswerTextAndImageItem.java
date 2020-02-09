package com.android.tupple.robot.view.testvocab.adapter.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerTextAndImageItem implements AnswerItem {

    private boolean isAnswer;
    private Vocabulary vocabulary;

    private AnswerTextAndImageItem(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public static AnswerTextAndImageItem create(Vocabulary vocabulary) {
        return new AnswerTextAndImageItem(vocabulary);
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

    public Vocabulary getVocabulary() {
        return this.vocabulary;
    }

}
