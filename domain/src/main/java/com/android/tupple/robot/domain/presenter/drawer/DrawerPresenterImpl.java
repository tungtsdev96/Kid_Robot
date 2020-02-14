package com.android.tupple.robot.domain.presenter.drawer;

import com.android.tupple.robot.domain.entity.menumain.DrawerViewPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

import java.util.ArrayList;

/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerPresenterImpl<MenuItemData> implements DrawerViewPresenter {

    private DrawerView<MenuItemData> mDrawerView;
    private DrawerModel<MenuItemData> mDrawerModel;

    private PresenterObserver<MenuType> mItemMenuSelectedObserver;

    public void setDrawerView(DrawerView<MenuItemData> drawerView) {
        this.mDrawerView = drawerView;
        // TODO initObservable
        mDrawerView.getItemMenuSelectedObservable().subscribe(this::handleItemMenuSelected);
    }

    public void setDrawerModel(DrawerModel<MenuItemData> drawerModel) {
        this.mDrawerModel = drawerModel;
    }

    @Override
    public void init() {
    }

    @Override
    public void start() {
        loadMenu();
    }

    private void loadMenu() {
        mDrawerView.setListMenu(new ArrayList<MenuItemData>());
    }

    @Override
    public void setItemMenuSelectedObserver(PresenterObserver<MenuType> itemMenuSelectedObserver) {
        this.mItemMenuSelectedObserver = itemMenuSelectedObserver;
    }

    private void handleItemMenuSelected(MenuType menuItemData) {
        if (mItemMenuSelectedObserver != null) {
            mItemMenuSelectedObserver.onComplete(menuItemData);
        }
    }

    @Override
    public void stop() {
        // TODO stop presenter
    }

    @Override
    public void finish() {
        // TODO destroy, clear view and model
    }
}
