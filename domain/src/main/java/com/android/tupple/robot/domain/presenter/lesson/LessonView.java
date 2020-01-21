package com.android.tupple.robot.domain.presenter.lesson;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LessonView<LessonData> {

    void setDataList(List<LessonData> datas);

    CleanObservable<LessonData> getItemLessonClickedObservable();

}
