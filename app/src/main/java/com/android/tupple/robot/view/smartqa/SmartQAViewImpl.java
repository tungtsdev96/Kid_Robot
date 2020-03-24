package com.android.tupple.robot.view.smartqa;

import android.app.Activity;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.music.MultiPlayer;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAView;
import com.android.tupple.robot.utils.SingleClickUtil;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAViewImpl implements SmartQAView<QAResponse> {

    private Activity mActivity;
    private MultiPlayer mMultiPlayer;

    private CleanObserver mBtnCloseButtonClicked;

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
    }

    @Override
    public void showResult(QAResponse qaResponse) {
        if (qaResponse.getResultText() == null || qaResponse.getResultText().isEmpty())  {
            showError();
            return;
        }

//        mBubbleAnswerView.setText(qaResponse.getResultText());
        playAnswer(qaResponse.getLinkAudio());
    }

    private void playAnswer(String linkAudio) {
        mMultiPlayer.play(linkAudio);
    }

    @Override
    public void showError() {
//        mBubbleAnswerView.setText(mActivity.getResources().getString(R.string.text_cant_hear_that));
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
    }

    @Override
    public CleanObservable getCloseButtonClicked() {
        return CleanObservable.create(cleanObserver -> mBtnCloseButtonClicked = cleanObserver);
    }

}
