package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.robot.domain.entity.menumain.EntertainmentPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

public class EntertainmentPresenterImpl<Media> implements EntertainmentPresenter {

    private EntertainmentViewWrapper<Media> mEntertainmentViewWrapper;
    private EntertainmentView<Media> mEntertainmentView;
    private EntertainmentModel<Media> mEntertainmentModel;
    private PresenterObserver<Media> mItemVideoClickedObserver;
    private PresenterObserver<Media> mItemAudioClickedObserver;
    private boolean mIsLoadData = false;

    public EntertainmentPresenterImpl() {

    }

    public void setEntertainmentViewWrapper(EntertainmentViewWrapper<Media> entertainmentViewWrapper) {
        this.mEntertainmentViewWrapper = entertainmentViewWrapper;
        mEntertainmentViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
        // TODO innit Observerable
    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ENTERTAINMENT;
    }

    public void setEntertainmentModel(EntertainmentModel<Media> entertainmentModel) {
        this.mEntertainmentModel = entertainmentModel;
    }

    private void handleItemVideoClicked(Media media) {
        if (mItemVideoClickedObserver != null) {
            mItemVideoClickedObserver.onComplete(media);
        }
    }

    private void handleItemAudioClicked(Media media) {
        if (mItemAudioClickedObserver != null) {
            mItemAudioClickedObserver.onComplete(media);
        }
    }

    @Override
    public void init() {
        mEntertainmentViewWrapper.show();
        mIsLoadData = false;
    }

    private void onViewCreated(EntertainmentView entertainmentView) {
        this.mEntertainmentView = entertainmentView;
        start();
        initObserable();
        // TODO innit Observerable
    }

    private void initObserable() {
        mEntertainmentView.getItemVideoClickedObservable().subscribe(this::handleItemVideoClicked);
        mEntertainmentView.getItemAudioClickedObservable().subscribe(this::handleItemAudioClicked);
    }

    @Override
    public void start() {
        if (!mIsLoadData) {
            requestVideoData();
            requestAudioData();
            mIsLoadData = true;
        }
    }

    private void requestVideoData() {
        mEntertainmentModel.getAllVideo().subscribe(mEntertainmentView::setListVideo);
    }
    private void requestAudioData() {
        mEntertainmentModel.getAllAudio().subscribe(mEntertainmentView::setListAudio);
    }
    public void setOnItemVideoClickObserver(PresenterObserver<Media> PresenterObserver) {
        this.mItemVideoClickedObserver = PresenterObserver;
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
