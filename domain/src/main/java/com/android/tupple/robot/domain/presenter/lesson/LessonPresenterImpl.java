package com.android.tupple.robot.domain.presenter.lesson;

import com.android.tupple.robot.domain.entity.lesson.LessonPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonPresenterImpl<LessonData> implements LessonPresenter {

    private LessonViewWrapper<LessonData> mLessonViewWrapper;
    private LessonView<LessonData> mLessonView;
    private LessonModel<LessonData> mLessonModel;

    private PresenterObserver<LessonData> mItemLessonClickedObserver;

    public LessonPresenterImpl(){

    }

    public void setLessonViewWrapper(LessonViewWrapper<LessonData> lessonViewWrapper) {
        this.mLessonViewWrapper = lessonViewWrapper;
        mLessonViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    private void onViewCreated(LessonView<LessonData> lessonView) {
        this.mLessonView = lessonView;

        // TODO init Observable
        mLessonView.getItemLessonClickedObservable().subscribe(lessonData -> {
           if (mItemLessonClickedObserver != null) {
               mItemLessonClickedObserver.onComplete(lessonData);
           }
        });
    }

    public void setLessonModel(LessonModel<LessonData> lessonModel) {
        this.mLessonModel = lessonModel;
    }

    @Override
    public void init() {
        mLessonViewWrapper.show();
    }

    @Override
    public void start() {
        requestData();
    }

    private void requestData() {
        mLessonModel.getListLessonData().subscribe(mLessonView::setDataList);
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

    public void setItemLessonClickedObserver(PresenterObserver<LessonData> itemLessonClickedObserver) {
        this.mItemLessonClickedObserver = itemLessonClickedObserver;
    }

}