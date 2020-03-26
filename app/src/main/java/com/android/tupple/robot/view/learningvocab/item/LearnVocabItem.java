package com.android.tupple.robot.view.learningvocab.item;

import com.android.tupple.robot.data.entity.Vocabulary;

/**
 * Created by tungts on 2020-01-18.
 */

public abstract class LearnVocabItem {

    protected Vocabulary mVocabulary;

    LearnVocabItem(Vocabulary vocabulary) {
        this.mVocabulary = vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.mVocabulary = vocabulary;
    }

    public String getVocabImage() {
        return mVocabulary.getImageUrl();
    }

    public String getVocabEn() {
        return mVocabulary.getVocabEn();
    }

    public String getSound() {
        return mVocabulary.getAudioUrl();
    }

    public int getTopicId() {
        return mVocabulary.getTopicId();
    }

    public int getLessonId() {
        return mVocabulary.getLessonId();
    }

    public String getAudioUrl() {
        return mVocabulary.getAudioUrl();
    }

    public int getVocabId() {
        return mVocabulary.getVocabId();
    }

    public abstract int getType();

}