package com.android.tupple.robot.view.testvocab.level1.item;

import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerTextItem implements AnswerItem {

    private String text;
    private boolean isAnswer;

    private AnswerTextItem(String text, boolean isAnswer) {
        this.text = text;
        this.isAnswer = isAnswer;
    }

    public static AnswerTextItem create(String text, boolean isAnswer) {
        return new AnswerTextItem(text, isAnswer);
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.TEXT;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

}
