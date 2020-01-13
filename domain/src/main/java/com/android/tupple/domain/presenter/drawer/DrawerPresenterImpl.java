package com.android.tupple.domain.presenter.drawer;

import com.android.tupple.domain.entity.menumain.DrawerViewPresenter;

import java.util.ArrayList;

/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerPresenterImpl<MenuItemData> implements DrawerViewPresenter {

    private DrawerView<MenuItemData> mDrawerView;
    private DrawerModel<MenuItemData> mDrawerModel;

    public void setDrawerView(DrawerView<MenuItemData> drawerView) {
        this.mDrawerView = drawerView;
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
    public void stop() {

    }
}
