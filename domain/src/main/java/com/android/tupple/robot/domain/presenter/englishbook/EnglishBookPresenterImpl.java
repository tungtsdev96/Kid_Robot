package com.android.tupple.robot.domain.presenter.englishbook;

import com.android.tupple.robot.domain.entity.menumain.EnglishBookPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;

/**
 * Created by tungts on 2020-01-12.
 */

public class EnglishBookPresenterImpl<SchoolBook> implements EnglishBookPresenter {

    private EnglishBookViewWrapper<SchoolBook> mEnglishBookViewWrapper;
    private EnglishBookView<SchoolBook> mEnglishBookView;
    private EnglishBookModel<SchoolBook> mEnglishBookModel;

    public EnglishBookPresenterImpl() {
    }

    public void setEnglishTopicViewWrapper(EnglishBookViewWrapper<SchoolBook> englishBookViewWrapper) {
        this.mEnglishBookViewWrapper = englishBookViewWrapper;
        mEnglishBookViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    public void setEnglishBookModel(EnglishBookModel<SchoolBook> englishBookModel) {
        this.mEnglishBookModel = englishBookModel;
    }

    @Override
    public void init() {
        mEnglishBookViewWrapper.show();
    }

    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        // TODO handle emply data or error
        mEnglishBookModel.getListBook().subscribe(mEnglishBookView::setListData);
    }

    private void onViewCreated(EnglishBookView<SchoolBook> englishBookView) {
        this.mEnglishBookView = englishBookView;
        // TODO innit Observerable
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ENGLISH_BOOK;
    }
}
