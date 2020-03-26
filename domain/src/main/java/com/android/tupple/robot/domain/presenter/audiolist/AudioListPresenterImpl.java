package com.android.tupple.robot.domain.presenter.audiolist;

import android.util.Log;

import com.android.tupple.robot.domain.entity.medialist.MediaListPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;

import java.util.ArrayList;
import java.util.List;

public class AudioListPresenterImpl<Media> implements MediaListPresenter {
    private AudioListViewWrapper<Media> mAudioListViewWrapper;
    private AudioListView<Media> mAudioListView;
    private AudioListModel<Media> mAudioListModel;
   // private PresenterObserver<Media> mItemAudioClickedObserver;
    private boolean mIsLoadData = false;
    private Media mCurrentAudio;
    private int mCurrentPosition;
    private boolean isPlay = false;
    private List<Media> mListAudio = new ArrayList<>();
    public AudioListPresenterImpl() {
    }
    public void setAudioListViewWrapper(AudioListViewWrapper<Media> audioListViewWrapper) {
        this.mAudioListViewWrapper = audioListViewWrapper;
        mAudioListViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
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
        mAudioListViewWrapper.show();
        mIsLoadData = false;
       // mAudioListView.initLayout();

    }

    private void onViewCreated(AudioListView audioListView) {
        this.mAudioListView = audioListView;
        //mAudioListView.setCurrentAudio(mCurrentAudio);
        start();
        initObserable();
        // TODO innit Observerable
    }
    private void initObserable() {
        mAudioListView.getItemAudioClickedObservable().subscribe(this::handleItemAudioClicked);
        mAudioListView.getNextButtonClickedObservable().subscribe(this::handleNextButton);
        mAudioListView.getPreviousButtonClickedObservable().subscribe(this::handlePreviousButton);
        mAudioListView.getStopPlayButtonClickedObservable().subscribe(this::handleStopPlayButton);

    }
    private void handleItemAudioClicked(Media media) {
        mAudioListView.setCurrentAudio(media);
        Log.d("AudioListPresenterImpl" , "prepare");
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
        mAudioListView.changeIconStopPlay(isPlay);
        if (isPlay) {
            mAudioListView.pauseAudio();
            isPlay = false;
        } else {
            mAudioListView.playAudio();
            isPlay = true;
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

    }


    @Override
    public void finish() {
        mAudioListView.stopAudio();
        mAudioListModel.cancel();
    }
}
