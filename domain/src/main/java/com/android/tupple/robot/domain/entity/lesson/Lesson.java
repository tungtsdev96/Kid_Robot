package com.android.tupple.robot.domain.entity.lesson;

import com.android.tupple.robot.domain.log.CLog;

/**
 * Created by tungts on 2020-01-18.
 */

public class Lesson {

    private LessonPresenter mLessonPresenter;

    public void setLessonPresenter(LessonPresenter mLessonPresenter) {
        this.mLessonPresenter = mLessonPresenter;
        if (mLessonPresenter != null) {
            // TODO start Observer

        }
    }

    public void init() {
        if (mLessonPresenter == null) {
            CLog.printE("Lesson", "LessonPresenter is null");
        }

        mLessonPresenter.init();
    }

    public void start() {
        if (mLessonPresenter == null) {
            CLog.printE("Lesson", "LessonPresenter is null");

        }

        mLessonPresenter.start();
    }

    public void stop() {

    }
}
