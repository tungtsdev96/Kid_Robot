package com.android.tupple.robot.model.drawer;

import android.content.Context;

import com.android.tupple.robot.data.entity.MenuItemData;
import com.android.tupple.robot.domain.presenter.drawer.DrawerModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerModelImpl implements DrawerModel<MenuItemData> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public DrawerModelImpl(Context context) {
        this.mContext = context;
    }
}
