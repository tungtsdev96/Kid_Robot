package com.android.tupple.robot.model.english;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.common.data.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishBookModelImpl implements EnglishBookModel<SchoolBook> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EnglishBookModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public CleanObservable<List<SchoolBook>> getListBook() {
        return CleanObservable.create(
                cleanObserver -> {
                   cleanObserver.onNext(SchoolBook.fakeData());
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
