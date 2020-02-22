package com.android.tupple.robot.data.model.alarm;

import android.content.Context;

import com.android.tupple.robot.domain.presenter.alarm.AlarmModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmModelImpl implements AlarmModel {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public AlarmModelImpl(Context context) {
        this.mContext = context;
    }
}
