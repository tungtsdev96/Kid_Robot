package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.domain.entity.lesson.Lesson;
import com.android.tupple.robot.domain.presenter.lesson.LessonModel;
import com.android.tupple.robot.domain.presenter.lesson.LessonPresenterImpl;
import com.android.tupple.robot.domain.presenter.lesson.LessonViewWrapper;
import com.android.tupple.robot.data.model.lesson.LessonModelFactory;
import com.android.tupple.robot.utils.constant.BookConstant;
import com.android.tupple.robot.view.lesson.LessonViewWrapperFactory;

/**
 * Created by tungts on 2020-01-17.
 */

public class LessonActivity extends BaseActivity {

    private Lesson mLesson;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_lesson;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        initFirstBatch(savedInstanceState);
        inject(savedInstanceState);

        mLesson.init();
    }

    private void initFirstBatch(Bundle bundle) {
        mLesson = new Lesson();
        mActivityLauncher = new ActivityLauncher(this);
    }

    private void inject(Bundle bundle) {
        SchoolBook book = getIntent().getParcelableExtra(BookConstant.EXTRA_BOOK);
        if (book == null) {
            return;
        }

        LessonPresenterImpl<LessonData> lessonPresenter = new LessonPresenterImpl<>();
        LessonViewWrapper<LessonData> lessonViewWrapper = LessonViewWrapperFactory.newLessonViewWrapper(this, bundle);
        LessonModel<LessonData> lessonModel = LessonModelFactory.newLessonModel(this);

        lessonPresenter.setLessonViewWrapper(lessonViewWrapper);
        lessonPresenter.setLessonModel(lessonModel);
        lessonPresenter.setBookId(book.getIdBook());
        initLessonPresenterObserver(lessonPresenter);

        mLesson.setLessonPresenter(lessonPresenter);
    }

    private void initLessonPresenterObserver(LessonPresenterImpl<LessonData> lessonPresenter) {
        lessonPresenter.setItemLessonClickedObserver(mActivityLauncher::launchLearningVocabActivity);
    }

    @Override
    protected void onResume() {
        mLesson.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mLesson.stop();
        super.onDestroy();
    }
}
