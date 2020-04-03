package com.android.tupple.robot.domain.presenter.videolist;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

public class VideoListPresenterImpl<Media> implements MediaListPresenter {
    private VideoListView<Media> mVideoListView;
    private VideoListModel<Media> mVideoListModel;
    private PresenterObserver<Media> mItemVideoClickedObserver;
    private boolean mIsLoadData = false;
    private CloseButtonHandler mOnCloseButtonHandler;
    public VideoListPresenterImpl() {

    }
    public void setOnCloseButtonHandler(CloseButtonHandler onButtonCloseHandler) {
        this.mOnCloseButtonHandler = onButtonCloseHandler;
    }
    public void setmVideoListView(VideoListView<Media> mVideoListView) {
        this.mVideoListView = mVideoListView;
    }

    public void setVideoListModel(VideoListModel<Media> mVideoListModel) {
        this.mVideoListModel = mVideoListModel;
    }

    private void handleItemVideoClicked(Media media) {
       mVideoListView.showDialogDescription(media);
    }

    @Override
    public void init() {
        mVideoListView.initLayout();
        mIsLoadData = false;
        start();
        initObserable();

    }


    private void initObserable() {
        mVideoListView.getItemVideoClickedObservable().subscribe(this::handleItemVideoClicked);
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
        mVideoListModel.getAllVideo().subscribe(mVideoListView::setListVideo);
    }

    public void setOnItemVideoClickObserver(PresenterObserver<Media> PresenterObserver) {
        this.mItemVideoClickedObserver = PresenterObserver;
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }
}
