package com.android.tupple.robot.view.smartqa;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.android.tupple.robot.R;
import com.android.tupple.trigger.TriggerService;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 3/23/20.
 * This class use speed to text from google.
 */

public class RecognitionPopupView {

    private static final String TAG = "RecognitionPopupView";

    private static final long POPUP_DISPLAY_DELAY = 100;
    private static final int MAX_TRY_SHOW_COUNT = 2;
    private static final HashMap<Integer, RecognitionPopupView> sInstances = new HashMap<>();

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PopupWindow mRecognitionPopupView;
    private View mParentView;
    private OnRecognitionPopupListener mOnRecognitionPopupListener;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mTryShowCount = 0;

    private RecognitionProgressView mRecognitionProgressView;
    private boolean isHasResult = false;

    public static synchronized RecognitionPopupView getInstance(int hashCode) {
        synchronized (sInstances) {
            RecognitionPopupView instance = sInstances.get(hashCode);
            if (instance == null) {
                instance = new RecognitionPopupView();
                sInstances.put(hashCode, instance);
                Log.d(TAG, "create AnswerResultPopupView instance, context hash : " + hashCode);
            }
            return instance;
        }
    }

    public static void removeInstance(int hashCode) {
        synchronized (sInstances) {
            RecognitionPopupView instance = sInstances.remove(hashCode);
            if (instance != null) {
                Log.d(TAG, "remove AnswerResultPopupView instance, context hash : " + hashCode);
                instance.clear();
            }
        }
    }

    private RecognitionPopupView() {
    }

    public RecognitionPopupView setOnShowCompleteListener(OnRecognitionPopupListener listener) {
        mOnRecognitionPopupListener = listener;
        return this;
    }

    public RecognitionPopupView init(Resources res) {
        mTryShowCount = 0;
        mScreenWidth = res.getDisplayMetrics().widthPixels;
        mScreenHeight = res.getDisplayMetrics().heightPixels;

        hide();
        clear();
        return this;
    }

    public RecognitionPopupView setParentView(View itemView) {
        mParentView = itemView;
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

        mRecognitionPopupView = createPopupWindow(x, y);

        if (mRecognitionPopupView == null) {
            return;
        }

        mRecognitionPopupView.update();

        if (mOnRecognitionPopupListener != null) {
            mOnRecognitionPopupListener.onShowComplete();
        }
    }

    private PopupWindow createPopupWindow(int x, int y) {
        View popupView = initLayout();

        if (popupView == null) {
            return null;
        }

        Resources res = mParentView.getContext().getResources();

        final int width = mScreenWidth;
        final int height = getMeasuredHeight(popupView, width);

        PopupWindow window = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(popupView);
        window.setWidth(width);
        window.setHeight(height);
        window.setOutsideTouchable(false);
        window.setAnimationStyle(R.style.StylePopupResultAnswer);
        window.showAtLocation(mParentView, Gravity.CENTER, mParentView.getWidth() / 2 - x / 2, mParentView.getHeight() / 2 - y / 2);

        return window;
    }

    private View initLayout() {
        Context context = mParentView.getContext();

        if (context == null) {
            return null;
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_popup_speed_to_text, null);
        int[] colors = getColors(context);

        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        mRecognitionProgressView = view.findViewById(R.id.recognition_view);
        mRecognitionProgressView.setColors(colors);
        mRecognitionProgressView.play();
        mRecognitionProgressView.setSpeechRecognizer(speechRecognizer);
        mRecognitionProgressView.setRecognitionListener(mRecognitionListenerAdapter);
        startRecognize(speechRecognizer);
        return view;
    }

    private RecognitionListenerAdapter mRecognitionListenerAdapter = new RecognitionListenerAdapter() {
        @Override
        public void onResults(Bundle results) {
            ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            isHasResult = result != null && !result.isEmpty();
            handleResultRecognize(isHasResult ? result.get(0) : null);
        }

        @Override
        public void onError(int error) {
            super.onError(error);
            handleErrorRecognize(error);
        }

        @Override
        public void onEndOfSpeech() {
            super.onEndOfSpeech();
            Log.d(TriggerService.TAG, "onEndOfSpeech");
            if (!isHasResult) {
                mRecognitionProgressView.stop();
                hide();
            }
        }
    };

    private void handleResultRecognize(String result) {
        if (result == null) {
            if (mOnRecognitionPopupListener != null) {
                mOnRecognitionPopupListener.onError(SpeechRecognizer.ERROR_NETWORK);
            }
            hide();
            return;
        }

        mRecognitionProgressView.stop();
        if (mOnRecognitionPopupListener != null) {
            mOnRecognitionPopupListener.onResult(result);
        }
        hide();
    }

    private void handleErrorRecognize(int error) {
        switch (error) {
            case SpeechRecognizer.ERROR_NETWORK:
                if (mOnRecognitionPopupListener != null) {
                    mOnRecognitionPopupListener.onError(SpeechRecognizer.ERROR_NETWORK);
                }
                hide();
                break;
        }
    }

    private void startRecognize(SpeechRecognizer speechRecognizer) {
        Intent intent = new Intent(RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);
        speechRecognizer.startListening(intent);
    }

    private int[] getColors(Context context) {
        return new int[]{
                ContextCompat.getColor(context, R.color.color1),
                ContextCompat.getColor(context, R.color.color2),
                ContextCompat.getColor(context, R.color.color3),
                ContextCompat.getColor(context, R.color.color4),
                ContextCompat.getColor(context, R.color.color5)
        };
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
        return mRecognitionPopupView != null && mRecognitionPopupView.isShowing();
    }

    public void hide() {
        if (!isShowing()) {
            return;
        }

        mRecognitionPopupView.dismiss();
        mRecognitionPopupView = null;
    }

    private void clear() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public interface OnRecognitionPopupListener {
        void onShowComplete();
        void onResult(String result);
        void onError(int type);
    }

}
