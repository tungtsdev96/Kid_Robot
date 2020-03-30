package com.android.tupple.robot.domain.presenter.englishbook;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface EnglishBookModel<SchoolBook> extends IModel {

    CleanObservable<List<SchoolBook>> getListBook();

}
