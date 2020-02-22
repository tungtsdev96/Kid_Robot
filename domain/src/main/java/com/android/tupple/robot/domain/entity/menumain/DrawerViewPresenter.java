package com.android.tupple.robot.domain.entity.menumain;

import com.android.tupple.robot.domain.entity.IPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-01-12.
 */

public interface DrawerViewPresenter extends IPresenter {

    void setItemMenuSelectedObserver(PresenterObserver<MenuType> itemMenuSelectedObserver);

}
