package com.android.tupple.robot.view.testvocab.level3;

import com.android.tupple.robot.data.entity.Vocabulary;

/**
 * Created by tungts on 2020-02-21.
 */

public class CheckPronounceItem {

    private Vocabulary vocabulary;

    private boolean isHasAnswer;
    private String urlAudio;
    private String yourAnswer;

    private boolean isRecording;
    private float amplitude;

    public CheckPronounceItem(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public String getVocabularyEn() {
        return vocabulary.getVocabEn();
    }

    public String getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    public boolean isHasAnswer() {
        return isHasAnswer;
    }

    public void setHasAnswer(boolean hasAnswer) {
        isHasAnswer = hasAnswer;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }
}
