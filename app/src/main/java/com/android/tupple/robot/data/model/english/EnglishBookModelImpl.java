package com.android.tupple.robot.data.model.english;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.SchoolBookDao;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.utils.RxUtils;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishBookModelImpl implements EnglishBookModel<SchoolBook> {

    private Context mContext;
    private SchoolBookDao mSchoolBookDao;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EnglishBookModelImpl(Context mContext) {
        this.mContext = mContext;
        this.mSchoolBookDao = KidRobotDatabase.getInstance(mContext).schoolBookDao();
    }

    @Override
    public CleanObservable<List<SchoolBook>> getListBook() {
        return CleanObservable.create(
                cleanObserver -> {
                    Disposable disposable = mSchoolBookDao
                            .loadAllBook()
                            .compose(RxUtils.async())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace);

                    mCompositeDisposable.add(disposable);
                }
        );
    }

    @Override
    public void cancel() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void destroy() {
    }
}
