package com.android.tupple.robot.domain.presenter.alarm;

import com.android.tupple.robot.domain.entity.menumain.AlarmPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmPresenterImpl implements AlarmPresenter {

    private AlarmViewWrapper mAlarmViewWrapper;
    private AlarmView mAlarmView;
    private AlarmModel mAlarmModel;

    public void setAlarmViewWrapper(AlarmViewWrapper alarmViewWrapper) {
        this.mAlarmViewWrapper = alarmViewWrapper;
    }

    public void setAlarmView(AlarmView alarmView) {
        this.mAlarmView = alarmView;
    }

    public void setAlarmModel(AlarmModel alarmModel) {
        this.mAlarmModel = alarmModel;
    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ALARM_CLOCK;
    }

    @Override
    public void init() {
        if (mAlarmViewWrapper != null) {
            mAlarmViewWrapper.show();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
