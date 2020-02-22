package com.android.tupple.robot.view.testvocab.adapter.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerTextItem implements AnswerItem {

    private String text;
    private boolean isAnswer;

    private AnswerTextItem(String text) {
        this.text = text;
    }

    public static AnswerTextItem create(Vocabulary vocabulary) {
        return new AnswerTextItem(vocabulary.getVocabEn());
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.TEXT;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

    public String getText() {
        return this.text;
    }

}
