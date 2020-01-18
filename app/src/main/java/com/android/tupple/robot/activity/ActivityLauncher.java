package com.android.tupple.robot.activity;

import android.app.Activity;
import android.content.Intent;

import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.common.data.SchoolBook;
import com.android.tupple.robot.utils.ActivityUtils;
import com.android.tupple.robot.utils.constant.BookConstant;
import com.android.tupple.robot.utils.constant.LessonConstant;

/**
 * Created by tungts on 2020-01-12.
 */

public class ActivityLauncher {

    private Activity mActivity;

    public ActivityLauncher(Activity activity) {
        mActivity = activity;
    }

    public void launchUnitActivity(SchoolBook book) {
        Intent intent = new Intent(mActivity, LessonActivity.class);
        intent.putExtra(BookConstant.EXTRA_BOOK, book);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchLearningVocabActivity(LessonData lessonData) {
        Intent intent = new Intent(mActivity, LearningVocabActivity.class);
        intent.putExtra(LessonConstant.EXTRA_LESSON, lessonData);
        ActivityUtils.startActivty(mActivity, intent);

    }
}
