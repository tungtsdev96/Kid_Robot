package com.android.tupple.robot.domain.entity.medialist;

import com.android.tupple.robot.domain.entity.audioplayer.PlayAudioPresenter;

public class MediaList {
    private MediaListPresenter mMediaListPresenter;

    public void setMediaListPresenter(MediaListPresenter mediaListPresenter) {
        this.mMediaListPresenter = mediaListPresenter;
    }

    public void init() {
        mMediaListPresenter.init();
    }

    public void start() {
        mMediaListPresenter.start();
    }

    public void finish() {
        mMediaListPresenter.finish();
    }
}
