package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.robot.domain.entity.menumain.EntertainmentPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;

public class EntertainmentPresenterImpl<Fragment> implements EntertainmentPresenter {

    private EntertainmentViewWrapper<Fragment> mEntertainmentViewWrapper;
    private EntertainmentView<Fragment> mEntertainmentView;
    private EntertainmentModel<Fragment> mEntertainmentModel;
    private PresenterObserver<Fragment> mButtonVideoClickedObserver;
    private PresenterObserver<Fragment> mButtonAudioClickedObserver;
    private VideoListPresenterImpl mVideoListPresenter;
    private AudioListPresenterImpl mAudioListPresenter;
    private boolean mIsLoadData = false;

    public void setVideoListPresenter(VideoListPresenterImpl videoListPresenter){
        this.mVideoListPresenter = videoListPresenter;
    }
    public void setAudioListPresenter(AudioListPresenterImpl audioListPresenter){
        this.mAudioListPresenter = audioListPresenter;
    }

    public EntertainmentPresenterImpl() {

    }

    public void setEntertainmentViewWrapper(EntertainmentViewWrapper<Fragment> entertainmentViewWrapper) {
        this.mEntertainmentViewWrapper = entertainmentViewWrapper;
        mEntertainmentViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
        // TODO innit Observerable
    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ENTERTAINMENT;
    }

    public void setEntertainmentModel(EntertainmentModel<Fragment> entertainmentModel) {
        this.mEntertainmentModel = entertainmentModel;
    }

    private void handleButtonVideoClicked(Fragment fragment) {
        mVideoListPresenter.stop();
        if (mVideoListPresenter != null)
            mVideoListPresenter.init();
    }

    private void handleButtonAudioClicked(Fragment fragment) {
        mAudioListPresenter.stop();
        if (mAudioListPresenter != null)
            mAudioListPresenter.init();
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
        mVideoListPresenter.init();
        // TODO innit Observerable
    }

    private void initObserable() {
        mEntertainmentView.getButtonVideoClickedObservable().subscribe(this::handleButtonVideoClicked);
        mEntertainmentView.getButtonAudioClickedObservable().subscribe(this::handleButtonAudioClicked);
    }

    @Override
    public void start() {
    }


    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
