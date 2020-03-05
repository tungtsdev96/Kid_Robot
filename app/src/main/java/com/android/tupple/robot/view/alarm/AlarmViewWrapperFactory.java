package com.android.tupple.robot.view.alarm;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.alarm.AlarmViewWrapper;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmViewWrapperFactory {

    public static AlarmViewWrapper newAlarmViewWrapper(FragmentManager fragmentManager) {
        return new AlarmViewWrapperImpl(fragmentManager);
    }

}
