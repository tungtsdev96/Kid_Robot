package com.android.tupple.robot.domain.entity.audioplayer;

public class PlayAudio {
    private PlayAudioPresenter mPlayAudioPresenter;

    public void setPlayAudioPresenter(PlayAudioPresenter playAudioPresenter) {
        this.mPlayAudioPresenter = playAudioPresenter;
    }

    public void init() {
        mPlayAudioPresenter.init();
    }

    public void start() {
        mPlayAudioPresenter.start();
    }

    public void finish() {
        mPlayAudioPresenter.finish();
    }
}
