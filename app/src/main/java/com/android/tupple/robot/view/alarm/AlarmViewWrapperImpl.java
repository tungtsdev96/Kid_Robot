package com.android.tupple.robot.view.alarm;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.alarm.AlarmViewWrapper;

/**
 * Created by tungts on 2/14/2020.
 */

public class AlarmViewWrapperImpl implements AlarmViewWrapper {

    private FragmentManager mFragmentManager;
    private AlarmFragment mAlarmFragment;

    public AlarmViewWrapperImpl(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void show() {
        createFragmentAlarm();
    }

    private void createFragmentAlarm() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mAlarmFragment = (AlarmFragment) mFragmentManager.findFragmentByTag(AlarmFragment.TAG);
        if (mAlarmFragment == null) {
            mAlarmFragment = AlarmFragment.newInstance();
        }
        fragmentTransaction.replace(R.id.content_menu, mAlarmFragment, AlarmFragment.TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }

}
