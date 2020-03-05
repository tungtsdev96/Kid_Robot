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

    private int mBookId = -1;

    private PresenterObserver<LessonData> mItemLessonClickedObserver;

    public LessonPresenterImpl(){
    }

    public void setBookId(int bookId) {
        mBookId = bookId;
    }

    public void setLessonViewWrapper(LessonViewWrapper<LessonData> lessonViewWrapper) {
        this.mLessonViewWrapper = lessonViewWrapper;
        mLessonViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    public void setLessonModel(LessonModel<LessonData> lessonModel) {
        this.mLessonModel = lessonModel;
    }

    private void onViewCreated(LessonView<LessonData> lessonView) {
        this.mLessonView = lessonView;
        initObservable();
    }

    private void initObservable() {
        mLessonView.getItemLessonClickedObservable().subscribe(this::handleLessonClicked);
    }

    private void handleLessonClicked(LessonData lesson) {
        if (mItemLessonClickedObserver != null) {
            mItemLessonClickedObserver.onComplete(lesson);
        }
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
        mLessonModel.getListLessonDataByBook(mBookId).subscribe(mLessonView::setDataList);
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {
        if (mLessonModel != null) {
            mLessonModel.destroy();
        }
    }

    public void setItemLessonClickedObserver(PresenterObserver<LessonData> itemLessonClickedObserver) {
        this.mItemLessonClickedObserver = itemLessonClickedObserver;
    }

}