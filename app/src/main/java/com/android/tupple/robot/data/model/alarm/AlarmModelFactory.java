package com.android.tupple.robot.data.model.alarm;

import android.content.Context;

import com.android.tupple.robot.domain.presenter.alarm.AlarmModel;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmModelFactory {

    public static AlarmModel newAlarmModel(Context context) {
        return new AlarmModelImpl(context);
    }

}
