package com.android.tupple.domain.presenter.drawer;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface DrawerView<MenuItemData> {

    void setListMenu(List<MenuItemData> items);

}
