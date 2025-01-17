package com.android.tupple.robot.data.model.lesson;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.LessonDao;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.domain.presenter.lesson.LessonModel;
import com.android.tupple.robot.utils.RxUtils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonModelImpl implements LessonModel<LessonData> {

    private Context mContext;
    private LessonDao mLessonDao;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public LessonModelImpl(Context mContext) {
        this.mContext = mContext;
        mLessonDao = KidRobotDatabase.getInstance(mContext).lessonDao();
    }

    @Override
    public CleanObservable<List<LessonData>> getListLessonData() {
        return CleanObservable.create(cleanObserver -> {
            cleanObserver.onNext(LessonData.fakeData());
        });
    }

    @Override
    public CleanObservable<List<LessonData>> getListLessonDataByBook(int bookId) {
        return CleanObservable.create(cleanObserver -> {
            Disposable disposable =
                    mLessonDao.loadListLessonByBook(bookId)
                    .compose(RxUtils.async())
                    .subscribe(cleanObserver::onNext, Throwable::printStackTrace);

            mCompositeDisposable.add(disposable);
        });
    }

    @Override
    public void cancel() {
        mCompositeDisposable.clear();
    }

    @Override
    public void destroy() {
        mCompositeDisposable.dispose();
    }
}
