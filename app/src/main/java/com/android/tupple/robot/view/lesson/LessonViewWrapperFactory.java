package com.android.tupple.robot.view.lesson;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.domain.presenter.lesson.LessonViewWrapper;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonViewWrapperFactory {

    public static LessonViewWrapper<LessonData> newLessonViewWrapper(AppCompatActivity activity, Bundle bundle) {
        return new LessonViewWrapperImpl(activity.getSupportFragmentManager(), bundle);
    }

}
