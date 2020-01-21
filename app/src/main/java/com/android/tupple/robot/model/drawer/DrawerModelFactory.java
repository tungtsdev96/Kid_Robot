package com.android.tupple.robot.model.drawer;

import android.content.Context;

import com.android.tupple.robot.common.data.MenuItemData;
import com.android.tupple.robot.domain.presenter.drawer.DrawerModel;

/**
 * Created by tungts on 2020-01-13.
 */

public class DrawerModelFactory {

    public static DrawerModel<MenuItemData> newDrawerModel(Context context) {
        return new DrawerModelImpl(context);
    }

}
