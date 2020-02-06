package com.android.tupple.robot.view.testvocab.level1.item;

import com.android.tupple.robot.utils.constant.TestVocabConstant;

/**
 * Created by tungts on 2020-02-06.
 */

public class AnswerImageItem implements AnswerItem {
    
    private String image;
    private boolean isAnswer;

    private AnswerImageItem(boolean isAnswer, String image) {
        this.image = image;
        this.isAnswer = isAnswer;
    }
    
    @Override
    public int getType() {
        return TestVocabConstant.ANSWER_TYPE.IMAGE;
    }

    @Override
    public boolean isAnswerTrue() {
        return isAnswer;
    }

}
