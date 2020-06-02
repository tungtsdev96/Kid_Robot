package com.android.tupple.robot.view.testvocab.adapter.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerImageItem implements AnswerChoose {

    private String image;
    private boolean isAnswer;
    private boolean isChoose = false;
    private int vocabId;

    private AnswerImageItem(Vocabulary vocabulary) {
        this.image = vocabulary.getImageUrl();
        this.vocabId = vocabulary.getVocabId();
    }

    public static AnswerImageItem create(Vocabulary vocabulary) {
        return new AnswerImageItem(vocabulary);
    }

    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.IMAGE;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

    @Override
    public int getVocabId() {
        return vocabId;
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

    public String getImage() {
        return this.image;
    }
}
