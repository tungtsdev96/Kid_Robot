package com.android.tupple.robot.data.model.questionanser;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-02-01.
 */

public class QAModelImpl {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public QAModelImpl(Context mContext) {
        this.mContext = mContext;
    }


}
