package com.android.tupple.robot.view.testvocab.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.testvocab.progress.TestProgressView;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created by tungts on 2020-03-09.
 */

public class TestProgressViewImpl implements TestProgressView {

    private Context mContext;

    private FloatingActionButton mBtnClose;
    private ProgressBar mProgressTest;

    private CleanObserver mBtnCloseClickedObserver;

    public TestProgressViewImpl(ViewGroup view) {
        this.mContext = view.getContext();
        View v = LayoutInflater.from(view.getContext()).inflate(R.layout.view_header_test_vocab, view);
        initViews(v);
    }

    private void initViews(View view) {
        mBtnClose = view.findViewById(R.id.btn_close);
        SingleClickUtil.registerListener(mBtnClose, v -> {
           if (mBtnCloseClickedObserver != null) {
               mBtnCloseClickedObserver.onNext();
           }
        });

        mProgressTest = view.findViewById(R.id.progress_test);
        mProgressTest.setProgress(0);
    }

    @Override
    public void setProgress(int progress) {
        mProgressTest.setProgress(progress);
    }

    @Override
    public CleanObservable getBtnCloseClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnCloseClickedObserver = cleanObserver);
    }
}
