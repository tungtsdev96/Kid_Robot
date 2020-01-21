package com.android.tupple.robot.view.learningvocab.item;

import com.android.tupple.robot.common.data.Vocabulary;

/**
 * Created by tungts on 2020-01-18.
 */

public abstract class LearnVocabItem {

    protected Vocabulary mVocabulary;

    public LearnVocabItem(Vocabulary vocabulary) {
        this.mVocabulary = vocabulary;
    }

    public String getVocabImage() {
        return mVocabulary.image;
    }

    public String getVocabEn() {
        return mVocabulary.vocabEn;
    }

    public String getSound() {
        return mVocabulary.audio;
    }

    public abstract int getType();

}
