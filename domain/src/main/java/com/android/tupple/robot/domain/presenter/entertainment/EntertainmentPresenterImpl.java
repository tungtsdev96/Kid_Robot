package com.android.tupple.robot.domain.presenter.entertainment;


import com.android.tupple.robot.domain.entity.entertainment.EntertainmentPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;

public class EntertainmentPresenterImpl<Fragment> implements EntertainmentPresenter {

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
    public void setEntertainmentView(EntertainmentView entertainmentView){
        this.mEntertainmentView = entertainmentView;
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
        mEntertainmentView.initLayout();
        mIsLoadData = false;
        start();
        initObserable();
        mVideoListPresenter.init();
    }

//    private void onViewCreated(EntertainmentView entertainmentView) {
//        this.mEntertainmentView = entertainmentView;
//        start();
//
//        // TODO innit Observerable
//    }

    private void initObserable() {
        mEntertainmentView.getButtonVideoClickedObservable().subscribe(this::handleButtonVideoClicked);
        mEntertainmentView.getButtonAudioClickedObservable().subscribe(this::handleButtonAudioClicked);
        mEntertainmentView.getButtonCloseClickedObservable().subscribe(this::closeEntertainmentActivity);
    }

    private void closeEntertainmentActivity() {
        mEntertainmentView.closeEntertainmentActivity();
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
