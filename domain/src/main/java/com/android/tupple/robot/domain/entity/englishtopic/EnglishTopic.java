package com.android.tupple.robot.domain.entity.englishtopic;

public class EnglishTopic {
    private MyEnglishTopicPresenter mMyEnglishTopicPresenter;
    public void setEnglishTopicPresenter(MyEnglishTopicPresenter englishPresenter) {
        this.mMyEnglishTopicPresenter = englishPresenter;
    }

    public void init() {
        mMyEnglishTopicPresenter.init();
    }

    public void start() {
        mMyEnglishTopicPresenter.start();
    }

    public void finish() {
        mMyEnglishTopicPresenter.finish();
    }
}
