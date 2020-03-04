package com.android.tupple.robot.view.testvocab.adapter.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerTextItem implements AnswerChoose {

    private String image;
    private boolean isAnswer;
    private boolean isChoose;

    private AnswerTextItem(Vocabulary vocabulary) {
        this.image = image;
    }

    public static AnswerTextItem create(Vocabulary vocabulary) {
        return new AnswerTextItem(vocabulary);
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.IMAGE;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

    public String getImage() {
        return this.image;
    }

    @Override
    public boolean isChoose() {
        return isChoose;
    }

    @Override
    public void setChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }

    @Override
    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }

}
