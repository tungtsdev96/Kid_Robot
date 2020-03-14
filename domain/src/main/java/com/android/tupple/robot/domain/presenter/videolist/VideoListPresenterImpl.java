package com.android.tupple.robot.domain.presenter.videolist;

import android.util.Log;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;

public class VideoListPresenterImpl<Media> implements MediaListPresenter {
    private VideoListViewWrapper<Media> mVideoListViewWrapper;
    private VideoListView<Media> mVideoListView;
    private VideoListModel<Media> mVideoListModel;
    private PresenterObserver<Media> mItemVideoClickedObserver;
    private boolean mIsLoadData = false;

    public VideoListPresenterImpl() {

    }

    public void setVideoListViewWrapper(VideoListViewWrapper<Media> videoListViewWrapper) {
        this.mVideoListViewWrapper = videoListViewWrapper;
        mVideoListViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    public void setmVideoListModel(VideoListModel<Media> mVideoListModel) {
        this.mVideoListModel = mVideoListModel;
    }

    private void handleItemVideoClicked(Media media) {
        if (mItemVideoClickedObserver != null) {
            mItemVideoClickedObserver.onComplete(media);
        }
    }

    @Override
    public void init() {
        mVideoListViewWrapper.show();
        mIsLoadData = false;
    }

    private void onViewCreated(VideoListView videoListView) {
        this.mVideoListView = videoListView;
        start();
        initObserable();
        // TODO innit Observerable
    }

    private void initObserable() {
        mVideoListView.getItemVideoClickedObservable().subscribe(this::handleItemVideoClicked);

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
