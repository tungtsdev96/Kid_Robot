package com.android.tupple.robot.view.testvocab.result;

import android.app.Activity;
import android.view.View;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultView;

/**
 * Created by tungts on 2020-02-09.
 */

public class AnswerResultViewImpl implements AnswerResultView, AnswerResultPopupView.OnAnswerResultPopupListener {

    private Activity mActivity;

    private CleanObserver mBtnClickedObserver;

    AnswerResultViewImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        AnswerResultPopupView.getInstance(mActivity.hashCode()).hide();
    }

    @Override
    public void invalidate() {

    }

    @Override
    public void showResult(boolean isResult, String result) {
        AnswerResultPopupView.getInstance(mActivity.hashCode())
                .init(mActivity.getResources())
                .setParentView(mActivity.findViewById(R.id.container_test_vocab))
                .setIsResult(isResult)
                .setResult(result)
                .setOnShowCompleteListener(this)
                .show();
    }

    @Override
    public void setTextResult(String result) {
    }

    @Override
    public CleanObservable getBtnContinueClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnClickedObserver = cleanObserver);
    }

    @Override
    public void onButtonContinueClicked() {
        if (mBtnClickedObserver != null) {
            mBtnClickedObserver.onNext();
        }
    }

    @Override
    public void onShowCompleted() {

    }
}
