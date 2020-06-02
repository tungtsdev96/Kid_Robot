package com.android.tupple.robot.view.smartqa;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.music.MultiPlayer;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAView;
import com.android.tupple.robot.utils.RxUtils;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAViewImpl implements SmartQAView<QAResponse>, RecognitionPopupView.OnRecognitionPopupListener, RecordingPopupView.OnRecordingPopupListener {

    private Activity mActivity;
    private MultiPlayer mMultiPlayer;

    private FloatingActionButton mAskRobot;
    private RecyclerView mRcvListQA;
    private TextQAAdapter mTextQAAdapter;

    private CleanObserver mBtnCloseButtonClicked;
    private CleanObserver mBtnAskRobotButtonClicked;
    private CleanObserver<String> mResultSpeedToTextObserver;
//    private CleanObserver<List<short[]>> mResultBufferAudioObserver;
    private CleanObserver<String> mResultBufferAudioObserver;

    public SmartQAViewImpl(Activity activity) {
        this.mActivity = activity;
        mMultiPlayer = new MultiPlayer(activity);
    }


    @Override
    public void initView() {
        SingleClickUtil.registerListener(mActivity.findViewById(R.id.btn_close), v -> {
           if (mBtnCloseButtonClicked != null) {
               mBtnCloseButtonClicked.onNext();
           }
        });
        mAskRobot = mActivity.findViewById(R.id.btn_ask_robot);
        SingleClickUtil.registerListener(mAskRobot,this::handleButtonAskRobot);
        initRcvListQA();
    }

    private void handleButtonAskRobot(View view) {
        if (RecognitionPopupView.getInstance(mActivity.hashCode()).isShowing()) {
            return;
        }

        if (mBtnAskRobotButtonClicked != null) {
            mBtnAskRobotButtonClicked.onNext();
        }
    }

    @Override
    public void showPopUpSpeedToText() {
//        RecognitionPopupView.getInstance(mActivity.hashCode())
//                .init(mActivity.getResources())
//                .setParentView(mActivity.findViewById(R.id.bubble_answer_view))
//                .setOnShowCompleteListener(this)
//                .setLanguage("VN")
//                .show();

        RecordingPopupView.getInstance(mActivity.hashCode())
                .init(mActivity.getResources())
                .setParentView(mActivity.findViewById(R.id.bubble_answer_view))
                .setOnShowCompleteListener(this)
                .show();
    }

    @Override
    public void updateFromRobot(int type) {
        switch (type) {
            default:
            case 0:
                mTextQAAdapter.addItem(0, mActivity.getResources().getString(R.string.text_way_to_ask_robot));
                break;
            case 1:
                mTextQAAdapter.addItem(2, null);
                break;
        }
    }

    private void initRcvListQA() {
        mRcvListQA = mActivity.findViewById(R.id.rcv_list_qa);
        mTextQAAdapter = new TextQAAdapter(mActivity, mRcvListQA);
        mRcvListQA.setLayoutManager(new LinearLayoutManager(mActivity));
        mRcvListQA.setAdapter(mTextQAAdapter);
    }

    @Override
    public void addResultFromRobot(QAResponse qaResponse) {
        if (qaResponse.getResultText() == null || qaResponse.getResultText().isEmpty())  {
            showHaveNotAnswer();
            return;
        }

        if (qaResponse.getQuestion() != null && !qaResponse.getQuestion().isEmpty()) {
            mTextQAAdapter.addItem(1, qaResponse.getQuestion());
        }

        mTextQAAdapter.addItem(0, qaResponse.getResultText());
        playAnswer(qaResponse.getLinkAudio());
//        mRcvListQA.scrollToPosition(mTextQAAdapter.getItemCount());
    }

    @Override
    public void addQuestionFromUser(String question) {
        mTextQAAdapter.addItem(1, question);
    }

    @Override
    public void showHaveNotAnswer() {
        mTextQAAdapter.addItem(0, mActivity.getResources().getString(R.string.text_have_not_answer));
    }

    private void playAnswer(String linkAudio) {
        mMultiPlayer.play(linkAudio);
    }

    @Override
    public void showError() {
        mTextQAAdapter.addItem(0, mActivity.getResources().getString(R.string.text_err_net_work));
    }

    @Override
    public void onStop() {
        if (mMultiPlayer != null && mMultiPlayer.isPlaying()) {
            mMultiPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        if (mMultiPlayer != null) {
            mMultiPlayer.destroy();
        }
//        RecognitionPopupView.removeInstance(mActivity.hashCode());
        RecordingPopupView.removeInstance(mActivity.hashCode());
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnCloseButtonClicked = cleanObserver);
    }

    @Override
    public CleanObservable getAskRobotButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnAskRobotButtonClicked = cleanObserver);
    }

    @Override
    public CleanObservable<String> getResultSpeedToTextObservable() {
        return CleanObservable.create(cleanObserver -> mResultSpeedToTextObserver = cleanObserver);
    }

    @Override
    public CleanObservable<String> getResultBufferAudioObservable() {
        return CleanObservable.create(cleanObserver -> mResultBufferAudioObserver = cleanObserver);
    }

    @Override
    public void onShowPopupSpeedToTextComplete() {

    }

    @Override
    public void onResultBuffer(String filePath) {
//        Log.d("Recording", bytesArray.length + "");
        if (mResultBufferAudioObserver != null) {
            mResultBufferAudioObserver.onNext(filePath);
            mTextQAAdapter.addItem(2, "Answering");
        }
    }

    @Override
    public void onError(int type) {
        mTextQAAdapter.addItem(0, mActivity.getResources().getString(R.string.text_cant_hear_that));
    }

    @Override
    public void onResultBuffer(short[] data) {

    }

    @Override
    public void onResultSpeedToText(String result) {
        addQuestionFromUser(result);
        if (mResultSpeedToTextObserver != null) {
            mResultSpeedToTextObserver.onNext(result);
            mTextQAAdapter.addItem(2, "Answering");
        }
    }

    @Override
    public void onErrorSpeedToText(int type) {
        mTextQAAdapter.addItem(0, mActivity.getResources().getString(R.string.text_cant_hear_that));
    }
}
