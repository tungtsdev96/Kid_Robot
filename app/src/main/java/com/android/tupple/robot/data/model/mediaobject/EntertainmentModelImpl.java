package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

import io.reactivex.disposables.CompositeDisposable;

public class EntertainmentModelImpl implements EntertainmentModel {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EntertainmentModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }
}
