package com.android.tupple.robot.domain.entity.entertainment;

import com.android.tupple.robot.domain.entity.englishtopic.MyEnglishTopicPresenter;

public class Entertainment {
    private EntertainmentPresenter entertainmentPresenter;
    public void setEntertainmentPresenter(EntertainmentPresenter entertainmentPresenter) {
        this.entertainmentPresenter = entertainmentPresenter;
    }

    public void init() {
        entertainmentPresenter.init();
    }

    public void start() {
        entertainmentPresenter.start();
    }

    public void finish() {
        entertainmentPresenter.finish();
    }
}
