package com.android.tupple.robot.domain.presenter.entertainment;


import com.android.tupple.robot.domain.entity.entertainment.EntertainmentPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

public class EntertainmentPresenterImpl implements EntertainmentPresenter {

    private EntertainmentView mEntertainmentView;
    private EntertainmentModel mEntertainmentModel;
    private PresenterObserver mButtonVideoClickedObserver;
    private PresenterObserver mButtonAudioClickedObserver;
    private PresenterObserver mButtonVideoYoutubeClickedObserver;
    private CloseButtonHandler mOnCloseButtonHandler;
    private boolean mIsLoadData = false;


    public void setEntertainmentView(EntertainmentView entertainmentView) {
        this.mEntertainmentView = entertainmentView;
    }

    public void setEntertainmentModel(EntertainmentModel entertainmentModel) {
        this.mEntertainmentModel = entertainmentModel;
    }

    private void handleButtonVideoClicked() {
        mButtonVideoClickedObserver.onComplete("string");
    }

    public void setCloseButtonHandler(CloseButtonHandler closeButtonHandler) {
        this.mOnCloseButtonHandler = closeButtonHandler;
    }

    private void handleButtonAudioClicked() {
        mButtonAudioClickedObserver.onComplete("string");
    }

    private void handleButtonVideoYoutubeClicked() {
        mButtonVideoYoutubeClickedObserver.onComplete("string");
    }

    @Override
    public void init() {
        mEntertainmentView.initLayout();
        mIsLoadData = false;
        start();
        initObserable();
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
        mEntertainmentView.getButtonCloseClickedObservable().subscribe(() -> {
            if (mOnCloseButtonHandler != null) {
                mOnCloseButtonHandler.onClose();
            }
        });
        mEntertainmentView.getButtonVideoYoutubeClickedObservable().subscribe(this::handleButtonVideoYoutubeClicked);
    }

    public void setmButtonVideoClickedObserver(PresenterObserver mButtonVideoClickedObserver) {
        this.mButtonVideoClickedObserver = mButtonVideoClickedObserver;
    }

    public void setmButtonAudioClickedObserver(PresenterObserver mButtonAudioClickedObserver) {
        this.mButtonAudioClickedObserver = mButtonAudioClickedObserver;
    }

    public void setmButtonVideoYoutubeClickedObserver(PresenterObserver mButtonVideoYoutubeClickedObserver) {
        this.mButtonVideoYoutubeClickedObserver = mButtonVideoYoutubeClickedObserver;
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
