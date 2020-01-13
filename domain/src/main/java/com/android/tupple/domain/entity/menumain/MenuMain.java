package com.android.tupple.domain.entity.menumain;

/**
 * Created by tungts on 2020-01-12.
 */

public class MenuMain {

    private DrawerViewPresenter mDrawerViewPresenter;
    private CurrentPresenter mCurrentPresenter;
    private MenuType mCurrentType;

    public void setDrawerViewPresenter(DrawerViewPresenter drawerViewPresenter) {
        this.mDrawerViewPresenter = drawerViewPresenter;
    }

    public void init(){
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.init();
        }

        if (mCurrentPresenter != null) {
            mCurrentPresenter.init();
        }
    }

    public void start() {
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.start();
        }

        if (mCurrentPresenter != null) {
            mCurrentPresenter.start();
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

}
