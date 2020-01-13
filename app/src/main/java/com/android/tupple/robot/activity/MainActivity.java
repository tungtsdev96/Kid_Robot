package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.domain.entity.menumain.MenuMain;
import com.android.tupple.domain.presenter.drawer.DrawerModel;
import com.android.tupple.domain.presenter.drawer.DrawerPresenterImpl;
import com.android.tupple.domain.presenter.drawer.DrawerView;
import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;
import com.android.tupple.robot.commondata.MenuItemData;
import com.android.tupple.robot.model.drawer.DrawerModelFactory;
import com.android.tupple.robot.view.drawer.DrawerViewFactory;

public class MainActivity extends BaseActivity {

    private MenuMain mMenuMain;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initFirstBatch(savedInstanceState);
        inject(savedInstanceState);

        mMenuMain.init();
    }

    private void initFirstBatch(Bundle bundle) {
        mMenuMain = new MenuMain();
        mActivityLauncher = new ActivityLauncher(this);
    }

    private void inject(Bundle bundle) {
        injectDrawer(bundle);
        injectEnglishBook(bundle);
        injectEnglishTopic(bundle);
    }

    private void injectEnglishTopic(Bundle bundle) {

    }

    private void injectEnglishBook(Bundle bundle) {

    }

    private void injectDrawer(Bundle bundle) {
        DrawerPresenterImpl<MenuItemData> drawerPresenter = new DrawerPresenterImpl<>();
        DrawerView<MenuItemData> drawerView = DrawerViewFactory.newDrawerView(this);
        DrawerModel<MenuItemData> drawerModel = DrawerModelFactory.newDrawerModel(this);

        drawerPresenter.setDrawerView(drawerView);
        drawerPresenter.setDrawerModel(drawerModel);
        mMenuMain.setDrawerViewPresenter(drawerPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuMain.start();
    }
}
