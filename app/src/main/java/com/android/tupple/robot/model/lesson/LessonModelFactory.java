package com.android.tupple.robot.model.lesson;

import android.content.Context;

import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.domain.presenter.lesson.LessonModel;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonModelFactory {

    public static LessonModel<LessonData> newLessonModel(Context context) {
        return new LessonModelImpl(context);
    }

}
