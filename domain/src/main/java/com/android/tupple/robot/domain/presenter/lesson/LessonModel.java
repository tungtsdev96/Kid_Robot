package com.android.tupple.robot.domain.presenter.lesson;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LessonModel<LessonData> extends IModel {

    CleanObservable<List<LessonData>> getListLessonData();

}
