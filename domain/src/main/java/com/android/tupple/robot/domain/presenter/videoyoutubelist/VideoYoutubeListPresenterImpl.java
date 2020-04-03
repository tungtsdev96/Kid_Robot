package com.android.tupple.robot.domain.presenter.videoyoutubelist;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

public class VideoYoutubeListPresenterImpl<Media> implements MediaListPresenter {
    private VideoYoutubeListView<Media> mVideoListView;
    private VideoYoutubeListModel<Media> mVideoListModel;
    private PresenterObserver<Media> mItemVideoYoutubeClickedObserver;
    private boolean mIsLoadData = false;
    private CloseButtonHandler mOnCloseButtonHandler;
    public VideoYoutubeListPresenterImpl() {

    }
    public void setOnCloseButtonHandler(CloseButtonHandler onButtonCloseHandler) {
        this.mOnCloseButtonHandler = onButtonCloseHandler;
    }
    public void setmVideoListView(VideoYoutubeListView<Media> mVideoListView) {
        this.mVideoListView = mVideoListView;
    }

    public void setVideoYoutubeListModel(VideoYoutubeListModel<Media> mVideoListModel) {
        this.mVideoListModel = mVideoListModel;
    }

    private void handleItemVideoClicked(Media media) {
        mItemVideoYoutubeClickedObserver.onComplete(media);
    }

    @Override
    public void init() {
        mVideoListView.initLayout();
        start();
        initObserable();
        mIsLoadData = false;
    }



    private void initObserable() {
        mVideoListView.getItemVideoYoutubeClickedObservable().subscribe(this::handleItemVideoClicked);
        mVideoListView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
    }
    private void handleCloseButton() {
        if (mOnCloseButtonHandler != null) {
            mOnCloseButtonHandler.onClose();
        }
    }
    @Override
    public void start() {
        if (!mIsLoadData) {
            requestVideoData();
            mIsLoadData = true;
        }
    }

    private void requestVideoData() {
        mVideoListModel.getAllVideoYoutube().subscribe(mVideoListView::setListVideoYoutube);
    }

    public void setOnItemVideoClickObserver(PresenterObserver<Media> PresenterObserver) {
        this.mItemVideoYoutubeClickedObserver = PresenterObserver;
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }
}
