package com.android.tupple.robot.domain.entity.menumain;

import android.util.Log;

import com.android.tupple.robot.domain.log.CLog;

/**
 * Created by tungts on 2020-01-12.
 */

public class MenuMain {

    private final static String TAG = "MenuMain";

    private MenuPresenterViewHolder mMenuPresenterViewHolder;

    private MenuPresenter mCurrentPresenter;
    private DrawerViewPresenter mDrawerViewPresenter;

    public MenuMain() {
        mMenuPresenterViewHolder = new MenuPresenterViewHolder();
    }

    public void setDrawerViewPresenter(DrawerViewPresenter drawerViewPresenter) {
        this.mDrawerViewPresenter = drawerViewPresenter;
        mDrawerViewPresenter.setItemMenuSelectedObserver(this::onMenuSwitched);
    }

    public void setEnglishTopicPresenter(EnglishTopicPresenter englishTopicPresenter) {
        this.mMenuPresenterViewHolder.setEnglishTopicPresenter(englishTopicPresenter);
    }

    public void setEnglishBookPresenter(EnglishBookPresenter englishBookPresenter) {
        this.mMenuPresenterViewHolder.setEnglishBookPresenter(englishBookPresenter);
    }

    public void setAlarmPresenter(AlarmPresenter alarmPresenter) {
        this.mMenuPresenterViewHolder.setAlarmPresenter(alarmPresenter);
    }

    public void setEntertainmentPresenter(EntertainmentPresenter entertainmentPresenter) {
        this.mMenuPresenterViewHolder.setEntertainmentPresenter(entertainmentPresenter);
    }

    public void init(){
        Log.d(TAG, "init " + mCurrentPresenter);

        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.init();
        }

        if (mCurrentPresenter != null) {
            mCurrentPresenter.init();
        }
    }

    // TODO start header, drawer, currentMenu
    public void start() {
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.start();
        }

        if (mCurrentPresenter != null) {
            mCurrentPresenter.start();
        }
    }

    public void setCurrentPresenterByMenuType(MenuType menuType) {
        mCurrentPresenter = mMenuPresenterViewHolder.get(menuType);
    }

    private void onMenuSwitched(MenuType menuType) {
        if (mCurrentPresenter != null && mCurrentPresenter.getMenuType() == menuType) {
            CLog.printD(TAG, "current presenter null: menuType = " + menuType);
            return;
        }

        changeMenu(menuType);
    }

    public void changeMenu(MenuType menuType) {
        if (mCurrentPresenter == null) {
            return;
        }

        mCurrentPresenter.stop();
        setCurrentPresenterByMenuType(menuType);
        updateMenuMain();
    }

    private void updateMenuMain() {
        // TODO update header, drawer,...
        if (mCurrentPresenter != null) {
            mCurrentPresenter.init();
        }
    }

    public void stop() {
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.stop();
        }

        if (mCurrentPresenter != null) {
            mCurrentPresenter.stop();
        }
    }

    public void finish() {
        if (mMenuPresenterViewHolder != null) {
            mMenuPresenterViewHolder.clear();
        }
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.finish();
            mDrawerViewPresenter = null;
        }

        mCurrentPresenter = null;
    }

}
