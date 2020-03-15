package com.android.tupple.robot.domain.presenter.audioplayer;

import android.util.Log;

import com.android.tupple.robot.domain.entity.audioplayer.PlayAudioPresenter;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class AudioPlayerPresenterImpl<Media> implements PlayAudioPresenter {

    public interface CloseButtonHandler {
        void onClose();
    }

    private final String TAG = "AudioPlayerPreImpl";
    private AudioPlayerView<Media> mAudioPlayerView;
    private AudioListModel<Media> mAudioPlayerModel;
    private Media mCurrentAudio;
    private int mCurrentPosition;
    private boolean isPlay = false;

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

    public void setAudioPlayerModel(AudioListModel<Media> audioPlayerModel) {
        this.mAudioPlayerModel = audioPlayerModel;
    }

    public void setCurrentAudio(Media currentAudio) {
        this.mCurrentAudio = currentAudio;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    public void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }

    private void initObservable() {
        mAudioPlayerView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
        mAudioPlayerView.getNextButtonClickedObservable().subscribe(this::handleNextButton);
        mAudioPlayerView.getPreviousButtonClickedObservable().subscribe(this::handlePreviousButton);
        mAudioPlayerView.getStopPlayButtonClickedObservable().subscribe(this::handleStopPlayButton);
    }

    @Override
    public void init() {
        Log.d(TAG, "init");
        mAudioPlayerView.initLayout();
        mAudioPlayerView.setCurrentAudio(mCurrentAudio);
        mAudioPlayerView.preparePlayer();
        start();
    }

    @Override
    public void start() {
        Log.d(TAG, "start");
        getAudioData();
        mAudioPlayerView.playAudio();
        isPlay = true;
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
        if (mCurrentPosition < mListAudio.size() - 1) {
            mCurrentPosition++;
        } else {
            mCurrentPosition = 0;
        }
        mAudioPlayerView.setCurrentAudio(mListAudio.get(mCurrentPosition));
        mAudioPlayerView.preparePlayer();

    }

    private void handlePreviousButton() {
        if (mCurrentPosition > 0) {
            mCurrentPosition--;
        } else {
            mCurrentPosition = mListAudio.size() - 1;
        }
        mAudioPlayerView.setCurrentAudio(mListAudio.get(mCurrentPosition));
        mAudioPlayerView.preparePlayer();

    }

    private void handleStopPlayButton() {
        mAudioPlayerView.changeIconStopPlay(isPlay);
        if (isPlay) {
            mAudioPlayerView.pauseAudio();
            isPlay = false;
        } else {
            mAudioPlayerView.playAudio();
            isPlay = true;
        }

    }

    @Override
    public void stop() {
        Log.d(TAG, "stop");
    }

    @Override
    public void finish() {
        Log.d(TAG, "finish");
        mAudioPlayerView.stopAudio();
        mAudioPlayerModel.cancel();
    }
}
