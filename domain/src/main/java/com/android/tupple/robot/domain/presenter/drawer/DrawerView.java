package com.android.tupple.robot.domain.presenter.drawer;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.entity.menumain.MenuType;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface DrawerView<MenuItemData> {

    void setListMenu(List<MenuItemData> items);

    CleanObservable<MenuType> getItemMenuSelectedObservable();

}
