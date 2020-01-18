package com.android.tupple.robot.view.drawer;

import android.app.Activity;

import com.android.tupple.robot.common.data.MenuItemData;
import com.android.tupple.robot.domain.presenter.drawer.DrawerView;


/**
 * Created by tungts on 2020-01-12.
 */

public class DrawerViewFactory {

    public static DrawerView<MenuItemData> newDrawerView(Activity activity) {
        return new DrawerViewImpl(activity);
    }

}
