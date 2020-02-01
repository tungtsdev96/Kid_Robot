package com.android.tupple.robot.domain.presenter.lesson;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LessonViewWrapper<Lesson> extends IView {

    CleanObservable<LessonView<Lesson>> getViewCreatedObservable();

}
