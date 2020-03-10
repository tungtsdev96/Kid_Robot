package com.android.tupple.robot.domain.presenter.audioplayer;

import com.android.tupple.robot.domain.entity.audioplayer.PlayAudioPresenter;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class AudioPlayerPresenterImpl<Media> implements PlayAudioPresenter {

    public interface CloseButtonHandler {
        void onClose();
    }

    private final String TAG = "AudioPlayerPresenterImpl";
    private AudioPlayerView<Media> mAudioPlayerView;
    private EntertainmentModel<Media> mAudioPlayerModel;
    private Media mCurrentAudio;
    private List<Media> mListAudio = new ArrayList<>();

    private CloseButtonHandler mOnCloseButtonHandler;
    public CloseButtonHandler getOnCloseButtonHandler() {
        return mOnCloseButtonHandler;
    }
    public void setOnCloseButtonHandler(CloseButtonHandler onButtonCloseHandler) {
        this.mOnCloseButtonHandler = onButtonCloseHandler;
    }
    public void setAudioPlayerView(AudioPlayerView<Media> audioPlayerView) {
        this.mAudioPlayerView = audioPlayerView;
        initObservable();
    }

    public void setAudioPlayerModel(EntertainmentModel<Media> audioPlayerModel) {
        this.mAudioPlayerModel = audioPlayerModel;
    }

    public void setCurrentAudio(Media currentAudio) {
        this.mCurrentAudio = currentAudio;
    }


    private void initObservable() {
        mAudioPlayerView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
        mAudioPlayerView.getNextButtonClickedObservable().subscribe(this::handleNextButton);
        mAudioPlayerView.getPreviousButtonClickedObservable().subscribe(this::handlePreviousButton);
    }

    @Override
    public void init() {
        mAudioPlayerView.initLayout();
        mAudioPlayerView.preparePlayer(mCurrentAudio);
        start();
    }

    @Override
    public void start() {
        getAudioData();
        mAudioPlayerView.playCurrentAudio(mCurrentAudio);
    }

    private void getAudioData() {
        mAudioPlayerModel.getAllAudio().subscribe(this::handleData);
    }

    private void handleData(List<Media> list) {
        mListAudio.clear();
        mListAudio.addAll(list);
    }

    private void handleCloseButton() {
        if (mOnCloseButtonHandler != null) {
            mOnCloseButtonHandler.onClose();
        }
    }

    private void handleNextButton() {

    }

    private void handlePreviousButton() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {
        mAudioPlayerModel.cancel();
    }
}
