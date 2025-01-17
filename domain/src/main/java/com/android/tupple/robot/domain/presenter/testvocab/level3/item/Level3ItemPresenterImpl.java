package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import com.android.tupple.robot.domain.entity.testvocab.Level3ItemPresenter;
import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.presenter.testvocab.level3.RecordState;
import com.android.tupple.robot.domain.presenter.testvocab.level3.ResultState;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemPresenterImpl<Vocabulary> implements Level3ItemPresenter {

    private final String TAG = "Level3ItemPresenter";

    private Level3ItemViewWrapper<Vocabulary> mLevel3ItemViewWrapper;
    private Level3ItemView<Vocabulary> mLevel3ItemView;
    private Level3ItemModel<Vocabulary> mLevel3ItemModel;

    private Vocabulary mVocabulary;
    private int mVocabId;
    private boolean isRecording;
    private boolean isTested;

    public Level3ItemPresenterImpl(Vocabulary vocabulary) {
        this.mVocabulary = vocabulary;
    }

    public void setLevel3ItemModel(Level3ItemModel<Vocabulary> level3ItemModel) {
        this.mLevel3ItemModel = level3ItemModel;
    }

    public void setLevel3ItemViewWrapper(Level3ItemViewWrapper<Vocabulary> level3ItemViewWrapper) {
        this.mLevel3ItemViewWrapper = level3ItemViewWrapper;
        this.mLevel3ItemViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    private void onViewCreated(Level3ItemView<Vocabulary> level3ItemView) {
        this.mLevel3ItemView = level3ItemView;
        initObservable();
        doOnStart();
    }

    private void initObservable() {
        mLevel3ItemView.getBtnPronounceClickedObservable().subscribe(this::handleBtnPronounceClicked);
        mLevel3ItemView.getBtnRecordingClickedObservable().subscribe(this::startRecord);
        mLevel3ItemView.getRecordStateDoneObservable().subscribe(this::handleOnRecordDone);
        mLevel3ItemView.getResultSpeedToTextObservable().subscribe(this::handleSpeedToText);
    }

    private void handleSpeedToText(String text) {
        mLevel3ItemView.setStateRecording(RecordState.WAITING);
        mLevel3ItemModel.getAnswerFromUserObservable(text, mVocabulary).subscribe(listRightCharacter -> this.handleResult(listRightCharacter, text));
    }

    private void handleResult(boolean[] listRightCharacter, String yourAnswer) {
        mLevel3ItemView.setStateRecording(RecordState.PREPARING);
        mLevel3ItemView.setTextYourAnswer(listRightCharacter, yourAnswer);
        isTested = true;
    }

    private void startRecord() {
        isRecording = true;
        mLevel3ItemView.startRecording(mVocabulary);
        mLevel3ItemView.setStateRecording(RecordState.RECORDING);
        mLevel3ItemView.setTextResultState(ResultState.INVALID);
    }

//    private void stopRecord() {
//        mLevel3ItemView.stopRecording();
//        mLevel3ItemView.setStateRecording(RecordState.WAITING);
//        mLevel3ItemView.setTextResult(ResultState.INVALID);
//    }

    private void handleOnRecordDone(String fileRecord) {
        // TODO handle with server
        CLog.printD(TAG, fileRecord);
        isRecording = false;
    }

    private void handleBtnPronounceClicked() {
        mLevel3ItemView.playPronounce(mVocabulary);
    }

    @Override
    public void init() {

    }

    @Override
    public void doOnStart() {
        mLevel3ItemView.setVocabulary(mVocabulary);
        mLevel3ItemView.setStateRecording(RecordState.PREPARING);
        mLevel3ItemView.setTextResultState(ResultState.INVALID);
    }

    @Override
    public void stop() {
        if (mLevel3ItemViewWrapper != null) {
            mLevel3ItemViewWrapper.hide();
        }
    }

    @Override
    public void onPageChange() {
        // TODO check is recording, stop record, check vocab is tested
    }

    @Override
    public void onPageSelected() {
        if (!isTested) {
            doOnStart();
        }
    }

    @Override
    public boolean checkCanNextVocab() {
        if (!isTested) {
            mLevel3ItemView.notifyHaveNotTested();
            return false;
        }

        if (isRecording) {
            mLevel3ItemView.stop();
        }

        return true;
    }


}
