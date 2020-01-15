package com.android.tupple.robot.domain.presenter.englishbook;


import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-01-15.
 */

public interface EnglishBookViewWrapper<Book> extends IView {

    CleanObservable<EnglishBookView<Book>> getViewCreatedObservable();

}
