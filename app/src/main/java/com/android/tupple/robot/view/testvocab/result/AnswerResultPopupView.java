package com.android.tupple.robot.view.testvocab.result;

import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.WindowManagerUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class AnswerResultPopupView {

    private static final String TAG = "AnswerResultPopupView";

    private static final long POPUP_DISPLAY_DELAY = 100;
    private static final int MAX_TRY_SHOW_COUNT = 2;
    private static final HashMap<Integer, AnswerResultPopupView> sInstances = new HashMap<>();

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PopupWindow mAnswerResultPopup;

    private View mParentView;
    private OnAnswerResultPopupListener mOnAnswerResultPopupListener;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mTryShowCount = 0;

    private boolean mIsResult;
    private String mResult;

    public static synchronized AnswerResultPopupView getInstance(int hashCode) {
        synchronized (sInstances) {
            AnswerResultPopupView instance = sInstances.get(hashCode);
            if (instance == null) {
                instance = new AnswerResultPopupView();
                sInstances.put(hashCode, instance);
                Log.d(TAG, "create AnswerResultPopupView instance, context hash : " + hashCode);
            }
            return instance;
        }
    }

    public static void removeInstance(int hashCode) {
        synchronized (sInstances) {
            AnswerResultPopupView instance = sInstances.remove(hashCode);
            if (instance != null) {
                Log.d(TAG, "remove AnswerResultPopupView instance, context hash : " + hashCode);
                instance.clear();
            }
        }
    }

    private AnswerResultPopupView() {
    }

    public AnswerResultPopupView setOnShowCompleteListener(OnAnswerResultPopupListener listener) {
        mOnAnswerResultPopupListener = listener;
        return this;
    }

    public AnswerResultPopupView init(Resources res) {
        mTryShowCount = 0;
        mScreenWidth = res.getDisplayMetrics().widthPixels;
        mScreenHeight = res.getDisplayMetrics().heightPixels;

        hide();
        clear();
        return this;
    }

    public AnswerResultPopupView setParentView(View itemView) {
        mParentView = itemView;
        return this;
    }

    public AnswerResultPopupView setIsResult(boolean isResult) {
        mIsResult = isResult;
        return this;
    }

    public AnswerResultPopupView setResult(String result) {
        mResult = result;
        return this;
    }

    public void show() {
        if (mParentView == null || mTryShowCount > MAX_TRY_SHOW_COUNT) {
            return;
        }

        mTryShowCount++;

        mCompositeDisposable.add(Single.timer(POPUP_DISPLAY_DELAY, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(z1 -> {
                    int[] position = new int[2];
                    if (!isValidPosition(position)) {
                        Log.e(TAG, "Invalid position, try again : " + mTryShowCount);
                        show();
                        return;
                    }

                    mTryShowCount = 0;

                    showTipCard(position);
                }));
    }

    private void showTipCard(int[] position) {
        if (isShowing()) {
            return;
        }

        final int x = position[0];
        final int y = position[1];

        mAnswerResultPopup = createPopupWindow(x, y);

        if (mAnswerResultPopup == null) {
            return;
        }

        mAnswerResultPopup.update();

        if (mOnAnswerResultPopupListener != null) {
            mOnAnswerResultPopupListener.onShowCompleted();
        }
    }

    private PopupWindow createPopupWindow(int x, int y) {
        View popupView = initLayout();

        Resources res = mParentView.getContext().getResources();
//        final int startMargin = res.getDimensionPixelSize(R.dimen.tip_card_start_margin);
//        final int endMargin = res.getDimensionPixelSize(R.dimen.tip_card_end_margin);

        final int width = mScreenWidth;
        final int height = getMeasuredHeight(popupView, width);

        PopupWindow window = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(popupView);
        window.setWidth(width);
        window.setHeight(height);
        window.setOutsideTouchable(false);
        window.setAnimationStyle(R.style.StylePopupResultAnswer);
        window.showAtLocation(mParentView, Gravity.START | Gravity.TOP, x, y - WindowManagerUtils.getStatusBarHeight(mParentView.getContext()));

        return window;
    }

    private View initLayout() {
        LayoutInflater inflater = LayoutInflater.from(mParentView.getContext());
        View view = inflater.inflate(R.layout.view_answer_result, null);

        RelativeLayout resultContainer = view.findViewById(R.id.result_container);
        TextView textNotifyResult = view.findViewById(R.id.text_notify_result);
        TextView textRightAnswer = view.findViewById(R.id.text_right_answer);

        resultContainer.setBackgroundResource(mIsResult ? R.color.bg_result_answer_right : R.color.bg_result_answer_wrong);
        textNotifyResult.setText(mIsResult ? mParentView.getContext().getString(R.string.header_answer_right) : mParentView.getContext().getString(R.string.header_answer_wrong));
        textRightAnswer.setText(String.format(mParentView.getContext().getString(R.string.answer_true), mResult));

        Button btnContinue = view.findViewById(R.id.btn_continue);
        btnContinue.setBackgroundResource(mIsResult ? R.drawable.bg_btn_continue_result_true : R.drawable.bg_btn_continue_result_false);
        btnContinue.setOnClickListener(v -> {
            if (mOnAnswerResultPopupListener != null) {
                mOnAnswerResultPopupListener.onButtonContinueClicked();
            }
        });

        return view;
    }

    private int getMeasuredHeight(View toolTipView, int width) {
        toolTipView.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return toolTipView.getMeasuredHeight();
    }

    private boolean isValidPosition(int[] position) {
        mParentView.getLocationInWindow(position);

        return position[0] >= 0 && position[1] >= 0 &&
                0 < mScreenWidth && position[0] < mScreenWidth;
    }

    private boolean isShowing() {
        return mAnswerResultPopup != null && mAnswerResultPopup.isShowing();
    }

    public void hide() {
        if (!isShowing()) {
            return;
        }

        mAnswerResultPopup.dismiss();
        mAnswerResultPopup = null;
    }

    public interface OnAnswerResultPopupListener {

        void onButtonContinueClicked();

        void onShowCompleted();
    }

    private void clear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

}
