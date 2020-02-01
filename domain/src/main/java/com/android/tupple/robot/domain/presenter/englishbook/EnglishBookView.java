package com.android.tupple.robot.domain.presenter.englishbook;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface EnglishBookView<Book> {

    void setListData(List<Book> listBooks);

    CleanObservable<Book> getOnItemBookClickedObservable();

    CleanObservable<Book> getOnItemBookLongClickedObservable();

}
