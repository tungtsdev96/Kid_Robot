package com.android.tupple.robot.view.drawer;

import com.android.tupple.robot.data.entity.MenuItemData;

/**
 * Created by tungts on 2020-01-13.
 */

public class DrawerMenuItem {

    private String mIcon;
    private String mTitle;

    public DrawerMenuItem(MenuItemData item) {
        this.mIcon = item.icon;
        this.mTitle = item.title;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getTitle() {
        return mTitle;
    }

}
