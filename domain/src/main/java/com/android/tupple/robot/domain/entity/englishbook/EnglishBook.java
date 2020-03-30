package com.android.tupple.robot.domain.entity.englishbook;

public class EnglishBook {
    private EnglishBookPresenter englishBookPresenter;
    public void setEnglishBookPresenter(EnglishBookPresenter englishBookPresenter) {
        this.englishBookPresenter = englishBookPresenter;
    }

    public void init() {
        englishBookPresenter.init();
    }

    public void start() {
        englishBookPresenter.start();
    }

    public void finish() {
        englishBookPresenter.finish();
    }
}
