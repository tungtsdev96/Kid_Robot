package com.android.tupple.robot.domain.presenter.videolist.dialog_description;

import android.util.Log;
import android.widget.Toast;

import com.android.tupple.robot.domain.entity.dialogdescription.DialogDescriptionPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;

public class DialogDescriptionPresenterImpl<Media> implements DialogDescriptionPresenter {
    private final String TAG = "DialogPreImpl";

    private DialogDescriptionView<Media> mDialogDescriptionView;
    private Media mCurrentVideo;
    private PresenterObserver<Media> mPlayButtonClickedObserver;
    private PresenterObserver<Media> mDownloadButtonClickedObserver;

    public DialogDescriptionView<Media> getDialogDescriptionView() {
        return mDialogDescriptionView;
    }

    public void setDialogDescriptionView(DialogDescriptionView<Media> mDialogDescriptionView) {
        this.mDialogDescriptionView = mDialogDescriptionView;
        initObservable();
    }


    public Media getCurrentVideo() {
        return mCurrentVideo;
    }

    public void setCurrentVideo(Media mCurrentVideo) {
        this.mCurrentVideo = mCurrentVideo;
    }

    private void initObservable() {
        mDialogDescriptionView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
        mDialogDescriptionView.getDownloadButtonClickedObservable().subscribe(this::handleDownloadButton);
        mDialogDescriptionView.getPlayButtonClickedObservable().subscribe(this::handlePlayButton);
    }

    private void handlePlayButton() {
        if (mPlayButtonClickedObserver != null) {
            mPlayButtonClickedObserver.onComplete(mCurrentVideo);
            mDialogDescriptionView.exitDialog();
        }
    }

    private void handleDownloadButton() {
        if (mDownloadButtonClickedObserver != null) {
            mDownloadButtonClickedObserver.onComplete(mCurrentVideo);
            mDialogDescriptionView.exitDialog();
        }

    }

    private void handleCloseButton() {
        mDialogDescriptionView.exitDialog();
    }

    @Override
    public void init() {
        mDialogDescriptionView.initLayout();
        mDialogDescriptionView.setInfo(mCurrentVideo);
        mDialogDescriptionView.showButtonDownloadOrPlay(mCurrentVideo);

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

    public void setOnPlayButtonClickedObserver(PresenterObserver<Media> PresenterObserver) {
        this.mPlayButtonClickedObserver = PresenterObserver;
    }

    public void setOnDownloadButtonClickedObserver(PresenterObserver<Media> PresenterObserver) {
        this.mDownloadButtonClickedObserver = PresenterObserver;
    }
}
