package com.android.tupple.robot.domain.presenter.audiolist;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;

public class AudioListPresenterImpl<Media> implements MediaListPresenter {
    private AudioListViewWrapper<Media> mAudioListViewWrapper;
    private AudioListView<Media> mAudioListView;
    private AudioListModel<Media> mAudioListModel;
    private PresenterObserver<Media> mItemAudioClickedObserver;
    private boolean mIsLoadData = false;

    public AudioListPresenterImpl() {
    }
    public void setAudioListViewWrapper(AudioListViewWrapper<Media> audioListViewWrapper) {
        this.mAudioListViewWrapper = audioListViewWrapper;
        mAudioListViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }
    public void setAudioListModel(AudioListModel<Media> mAudioListModel) {
        this.mAudioListModel = mAudioListModel;
    }

    @Override
    public void init() {
        mAudioListViewWrapper.show();
        mIsLoadData = false;
    }
    private void onViewCreated(AudioListView audioListView) {
        this.mAudioListView = audioListView;
        start();
        initObserable();
        // TODO innit Observerable
    }
    private void initObserable() {
        mAudioListView.getItemAudioClickedObservable().subscribe(this::handleItemAudioClicked);
    }
    private void handleItemAudioClicked(Media media) {
        if (mItemAudioClickedObserver != null) {
            mItemAudioClickedObserver.onComplete(media);
        }
    }
    @Override
    public void start() {
        if (!mIsLoadData) {
            requestAudioData();
            mIsLoadData = true;
        }
    }
    private void requestAudioData() {
        mAudioListModel.getAllAudio().subscribe(mAudioListView::setListAudio);
    }
    public void setOnItemAudioClickObserver(PresenterObserver<Media> PresenterObserver) {
        this.mItemAudioClickedObserver = PresenterObserver;
    }
    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }
}
