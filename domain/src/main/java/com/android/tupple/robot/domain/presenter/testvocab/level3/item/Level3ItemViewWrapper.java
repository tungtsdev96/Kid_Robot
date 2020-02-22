package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-02-22.
 */

public interface Level3ItemViewWrapper<Vocabulary> extends IView {

    CleanObservable<Level3ItemView<Vocabulary>> getViewCreatedObservable();

}
