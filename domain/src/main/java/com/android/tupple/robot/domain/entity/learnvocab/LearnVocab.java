package com.android.tupple.robot.domain.entity.learnvocab;

/**
 * Created by tung.ts on 1/15/2020.
 */

public class LearnVocab {

    private LearnVocabPresenter mLearnVocabPresenter;

    public void setLearnVocabPresenter(LearnVocabPresenter learnVocabPresenter) {
        this.mLearnVocabPresenter = learnVocabPresenter;
    }

    public void init() {
        mLearnVocabPresenter.init();
    }

    public void start() {
        mLearnVocabPresenter.start();
    }

    public void stop() {

    }
}
