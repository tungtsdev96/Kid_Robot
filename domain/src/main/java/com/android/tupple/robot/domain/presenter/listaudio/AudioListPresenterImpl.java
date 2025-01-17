package com.android.tupple.robot.domain.presenter.listaudio;

import android.util.Log;
import android.widget.Toast;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;

import java.util.ArrayList;
import java.util.List;

public class AudioListPresenterImpl<Media> implements MediaListPresenter {
    private AudioListView<Media> mAudioListView;
    private AudioListModel<Media> mAudioListModel;
    // private PresenterObserver<Media> mItemAudioClickedObserver;

    private CloseButtonHandler mOnCloseButtonHandler;
    private boolean mIsLoadData = false;
    private Media mCurrentAudio;
    private int mCurrentPosition = -1;
    private boolean isPlay = false;
    private List<Media> mListAudio = new ArrayList<>();

    public AudioListPresenterImpl() {
    }

    public void setOnCloseButtonHandler(CloseButtonHandler onButtonCloseHandler) {
        this.mOnCloseButtonHandler = onButtonCloseHandler;
    }

    public void setmAudioListView(AudioListView<Media> mAudioListView) {
        this.mAudioListView = mAudioListView;
    }

    public void setAudioListModel(AudioListModel<Media> mAudioListModel) {
        this.mAudioListModel = mAudioListModel;
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

    @Override
    public void init() {
        mAudioListView.initLayout();
        start();
        initObserable();
        mIsLoadData = false;
        // mAudioListView.initLayout();

    }


    private void initObserable() {
        mAudioListView.getItemAudioClickedObservable().subscribe(this::handleItemAudioClicked);
        mAudioListView.getNextButtonClickedObservable().subscribe(this::handleNextButton);
        mAudioListView.getPreviousButtonClickedObservable().subscribe(this::handlePreviousButton);
        mAudioListView.getStopPlayButtonClickedObservable().subscribe(this::handleStopPlayButton);
        mAudioListView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
    }

    private void handleCloseButton() {
        if (mOnCloseButtonHandler != null) {
            mOnCloseButtonHandler.onClose();
        }
    }

    private void handleItemAudioClicked(Media media) {

        mCurrentPosition = mAudioListView.getPositionByMedia(media);
        mAudioListView.setCurrentAudio(media);
        Log.d("AudioListPresenterImpl", "prepare");
        mAudioListView.preparePlayer();
        isPlay = true;
    }

    private void handleNextButton() {
        if (mCurrentPosition < mListAudio.size() - 1) {
            mCurrentPosition++;
        } else {
            mCurrentPosition = 0;
        }
        mAudioListView.scrollToItem(mCurrentPosition);
        mAudioListView.setCurrentAudio(mListAudio.get(mCurrentPosition));
        mAudioListView.preparePlayer();
        isPlay = true;

    }

    private void handlePreviousButton() {
        if (mCurrentPosition > 0) {
            mCurrentPosition--;
        } else {
            mCurrentPosition = mListAudio.size() - 1;
        }
        mAudioListView.scrollToItem(mCurrentPosition);
        mAudioListView.setCurrentAudio(mListAudio.get(mCurrentPosition));
        mAudioListView.preparePlayer();
        isPlay = true;

    }

    private void handleStopPlayButton() {
        if (mCurrentPosition == -1) {
            mCurrentPosition = 0;
            mAudioListView.setCurrentAudio(mListAudio.get(mCurrentPosition));
            mAudioListView.preparePlayer();
            isPlay = true;
        } else {
            mAudioListView.changeIconStopPlay(isPlay);

            Log.d("ahihi" , isPlay+" ");
            if (isPlay) {
                mAudioListView.pauseAudio();
                isPlay = false;
            } else {

                mAudioListView.playAudio();
                isPlay = true;
            }
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
        mAudioListModel.getAllAudio().subscribe(this::handleData);
    }

    private void handleData(List<Media> list) {
        mListAudio.clear();
        mListAudio.addAll(list);
    }

    @Override
    public void stop() {
        if (mAudioListView != null)
            mAudioListView.stopPlayer();
    }


    @Override
    public void finish() {
        mAudioListView.stopAudio();
        mAudioListModel.cancel();
    }
}
