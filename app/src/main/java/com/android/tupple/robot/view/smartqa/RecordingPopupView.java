package com.android.tupple.robot.view.smartqa;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.record.RecordingThread;
import com.android.tupple.robot.utils.RecordingHelper;
import com.android.tupple.robot.wavelineview.WaveLineView;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tungts on 4/22/20.
 */

public class RecordingPopupView implements RecordingThread.AudioDataReceivedListener {

    private static final String TAG = "RecordingPopupView";

    private static final long POPUP_DISPLAY_DELAY = 100;
    private static final int MAX_TRY_SHOW_COUNT = 2;
    private static final HashMap<Integer, RecordingPopupView> sInstances = new HashMap<>();

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PopupWindow mRecordingPopupView;
    private View mParentView;
    private OnRecordingPopupListener mOnRecordingPopupListener;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mTryShowCount = 0;

    private WaveLineView mWaveLineView;
    private TextView mTextTest;

    public static synchronized RecordingPopupView getInstance(int hashCode) {
        synchronized (sInstances) {
            RecordingPopupView instance = sInstances.get(hashCode);
            if (instance == null) {
                instance = new RecordingPopupView();
                sInstances.put(hashCode, instance);
                Log.d(TAG, "create RecordingPopupView instance, context hash : " + hashCode);
            }
            return instance;
        }
    }

    public static void removeInstance(int hashCode) {
        synchronized (sInstances) {
            RecordingPopupView instance = sInstances.remove(hashCode);
            if (instance != null) {
                Log.d(TAG, "remove RecordingPopupView instance, context hash : " + hashCode);
                instance.clear();
            }
        }
    }

    private RecordingPopupView() {
    }

    public RecordingPopupView setOnShowCompleteListener(OnRecordingPopupListener listener) {
        mOnRecordingPopupListener = listener;
        return this;
    }

    public RecordingPopupView init(Resources res) {
        mTryShowCount = 0;
        mScreenWidth = res.getDisplayMetrics().widthPixels;
        mScreenHeight = res.getDisplayMetrics().heightPixels;

        hide();
        clear();
        return this;
    }

    public RecordingPopupView setParentView(View itemView) {
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

        mRecordingPopupView = createPopupWindow(x, y);

        if (mRecordingPopupView == null) {
            return;
        }

        mRecordingPopupView.update();
    }

    private PopupWindow createPopupWindow(int x, int y) {
        View popupView = initLayout();

        if (popupView == null) {
            return null;
        }

        Resources res = mParentView.getContext().getResources();

        final int width = mParentView.getWidth() / 2;
        final int height = mParentView.getHeight() / 2;
        Log.d(TAG, "width " + width + " " + " height " + height);

        PopupWindow window = new PopupWindow(popupView, FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(popupView);
        window.setWidth(width);
        window.setHeight(height);
        window.setOutsideTouchable(false);
        window.setAnimationStyle(R.style.StylePopupResultAnswer);
        int[] parentPos = new int[2];
        mParentView.getLocationInWindow(parentPos);
        window.showAtLocation(mParentView, Gravity.NO_GRAVITY, parentPos[0] + (mParentView.getWidth() - width) / 2, parentPos[1] + (mParentView.getHeight() - height) / 2);
        return window;
    }

    private View initLayout() {
        Log.d(TAG, "initLayout");
        Context context = mParentView.getContext();

        if (context == null) {
            return null;
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_popup_recording, null);

        mWaveLineView = view.findViewById(R.id.waveline_view);
        mTextTest = view.findViewById(R.id.text_average);

        mCompositeDisposable.add(
                Single.timer(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(z -> {
                            mWaveLineView.onResume();
                            mWaveLineView.startAnim();
                            startRecording();
                        }));

        return view;
    }

    private Handler mHandlerRecord = new Handler();
    private RecordingThread mRecordingThread = new RecordingThread();
    private RecordingHelper mRecordingHelper;

    private void startRecording() {
        mRecordingThread.startRecording();
        mRecordingThread.setAudioDataReceivedListener(this);
//        if (mRecordingHelper == null) {
//            mRecordingHelper = new RecordingHelper(mParentView.getContext());
//        }
//        mRecordingHelper.startRecord("tst");
//        mHandlerRecord.postDelayed(this::stopRecording, 5000);
    }

    private void stopRecording() {
//        Log.d("RecordingThread", "stop  Recording");
//        mRecordingThread.stopRecording();
        if (mRecordingHelper != null) {
            File file = mRecordingHelper.stopRecord();

            if (file == null) {
                return;
            }

            if (mOnRecordingPopupListener != null) {
                mOnRecordingPopupListener.onResultBuffer(file.getAbsolutePath());
            }

//            byte[] bytesArray = new byte[(int) file.length()];
//
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(file);
//                fis.read(bytesArray); //read file into bytes[]
//                fis.close();
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }

        if (isShowing()) {
            hide();
        }
    }

    private boolean isValidPosition(int[] position) {
        mParentView.getLocationInWindow(position);

        return position[0] >= 0 && position[1] >= 0 &&
                0 < mScreenWidth && position[0] < mScreenWidth;
    }

    public boolean isShowing() {
        return mRecordingPopupView != null && mRecordingPopupView.isShowing();
    }

    private void hide() {
        if (!isShowing()) {
            return;
        }

        mRecordingPopupView.dismiss();
        mRecordingPopupView = null;
    }

    private void clear() {
        if (mWaveLineView != null) {
            mWaveLineView.release();
            mWaveLineView = null;
        }

        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onAudioDataReceived(short[] data) {
        if (mOnRecordingPopupListener != null) {
            Disposable d = Observable.fromCallable(() -> data)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(this::hide)
                    .subscribe(x -> {
                        if (mOnRecordingPopupListener != null) {
                            mOnRecordingPopupListener.onResultBuffer(data);
                        }
                    }, t -> Log.d("RecordingThread_", t.getLocalizedMessage()));
            mCompositeDisposable.add(d);
        }
    }


    double pre = 0;
    @Override
    public void onDataPerSecond(short[] buffer, boolean isStart) {
        Disposable d = Observable.fromCallable(() -> buffer)
                .map(buff -> {
                    double result = 0;
                    for (short tmp: buff) {
                        result += tmp * tmp;
                    }
                    return result / buff.length;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
                    if (isStart) {
                        mTextTest.setText("");
                    }
                    double k = Math.abs(x - pre);
                    pre = x;
                    mTextTest.setText(k + "");
                    Log.d("Recording", "Average " + k);
                    }, t -> Log.d("RecordingThread_", t.getLocalizedMessage()));
        mCompositeDisposable.add(d);
    }

    public interface OnRecordingPopupListener {

        void onError(int type);

        void onResultBuffer(short[] data);

        void onResultBuffer(String filePath);
    }

}
