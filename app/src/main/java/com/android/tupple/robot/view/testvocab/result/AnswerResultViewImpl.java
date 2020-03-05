package com.android.tupple.robot.view.testvocab.result;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * Created by tungts on 2020-02-09.
 */

public class AnswerResultViewImpl implements AnswerResultView {

    private Activity mActivity;
    private BottomSheetDialog mBottomSheetDialog;

    private RelativeLayout mResultContainer;
    private TextView mTextNotifyResult;
    private TextView mTextRightAnswer;
    private Button mBtnContinue;

    private CleanObserver mBtnClickedObserver;

    AnswerResultViewImpl(Activity activity) {
        this.mActivity = activity;
        View view = mActivity.getLayoutInflater().inflate(R.layout.view_bottom_sheet_answer_result, null);
        mBottomSheetDialog = new BottomSheetDialog(mActivity);
        mBottomSheetDialog.setContentView(view);
        initViews(view);
    }

    private void initViews(View view) {
        mResultContainer = view.findViewById(R.id.result_container);
        mTextNotifyResult = view.findViewById(R.id.text_notify_result);
        mTextRightAnswer = view.findViewById(R.id.text_right_answer);
        mBtnContinue = view.findViewById(R.id.btn_continue);
        mBtnContinue.setOnClickListener(v -> {
            if (mBtnClickedObserver != null) {
                mBtnClickedObserver.onNext();
            }
        });
    }

    @Override
    public void show() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.show();
        }
    }

    @Override
    public void hide() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.hide();
        }
    }

    @Override
    public void invalidate() {

    }

    @Override
    public void showResult(boolean isResult) {
        mResultContainer.setBackgroundResource(isResult ? R.color.bg_result_answer_right : R.color.bg_result_answer_wrong);
        mTextNotifyResult.setText(isResult ? mActivity.getString(R.string.header_answer_right) : mActivity.getString(R.string.header_answer_wrong));
        mBtnContinue.setBackgroundResource(isResult ? R.drawable.bg_btn_continue_result_true : R.drawable.bg_btn_continue_result_false);
        show();
    }

    @Override
    public void setTextResult(String result) {
        mTextRightAnswer.setText(String.format(mActivity.getString(R.string.answer_true), result));
    }

    @Override
    public CleanObservable getBtnContinueClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnClickedObserver = cleanObserver);
    }
}
