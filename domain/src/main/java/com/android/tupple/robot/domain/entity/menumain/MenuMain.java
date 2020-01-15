package com.android.tupple.robot.domain.entity.menumain;

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
        // TODO innit observer
        this.mDrawerViewPresenter = drawerViewPresenter;
    }

    public void setEnglishTopicPresenter(EnglishTopicPresenter englishTopicPresenter) {
        // TODO innit observer
        this.mMenuPresenterViewHolder.setEnglishTopicPresenter(englishTopicPresenter);
    }

    public void setEnglishBookPresenter(EnglishBookPresenter englishBookPresenter) {
        // TODO innit observer
        this.mMenuPresenterViewHolder.setEnglishBookPresenter(englishBookPresenter);
    }

    public void init(){
        if (mDrawerViewPresenter != null) {
            mDrawerViewPresenter.init();
        }

        mCurrentPresenter = mMenuPresenterViewHolder.get(MenuType.ENGLISH_BOOK);
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
