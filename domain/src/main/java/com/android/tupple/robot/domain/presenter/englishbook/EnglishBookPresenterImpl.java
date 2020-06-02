package com.android.tupple.robot.domain.presenter.englishbook;


import com.android.tupple.robot.domain.entity.englishbook.EnglishBookPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.CloseButtonHandler;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-01-12.
 */

public class EnglishBookPresenterImpl<SchoolBook> implements EnglishBookPresenter {

    private EnglishBookView<SchoolBook> mEnglishBookView;
    private EnglishBookModel<SchoolBook> mEnglishBookModel;

    private CloseButtonHandler mCloseButtonHandler;
    private PresenterObserver<SchoolBook> mOnItemBookClickedObserver;

    private boolean mIsInitializing = true;

    public EnglishBookPresenterImpl() {
    }

    public void setEnglishBookView(EnglishBookView<SchoolBook> mEnglishBookView) {
        this.mEnglishBookView = mEnglishBookView;
        initObservable();
    }

    public void setEnglishBookModel(EnglishBookModel<SchoolBook> englishBookModel) {
        this.mEnglishBookModel = englishBookModel;

    }

    @Override
    public void init() {
        mEnglishBookView.initLayout();
        mIsInitializing = true;
        start();
    }

    @Override
    public void start() {
        if (mIsInitializing) {
            loadData();
            mIsInitializing = false;
        }
    }

    private void loadData() {
        // TODO handle emply data or error
        mEnglishBookModel.getListBook().subscribe(mEnglishBookView::setListData);
    }

    private void initObservable() {
        mEnglishBookView.getOnItemBookClickedObservable().subscribe(this::handleItemBookClicked);
        mEnglishBookView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
    }


    private void handleItemBookClicked(SchoolBook book) {
        if (mOnItemBookClickedObserver != null) {
            mOnItemBookClickedObserver.onComplete(book);
        }
    }

    private void handleCloseButton() {
        if (mCloseButtonHandler != null) {
            mCloseButtonHandler.onClose();
        }
    }

    public void setCloseButtonHandler(CloseButtonHandler closeButtonHandler) {
        this.mCloseButtonHandler = closeButtonHandler;
    }


    public void setOnItemBookClickedObserver(PresenterObserver<SchoolBook> onItemBookClickedObserver) {
        this.mOnItemBookClickedObserver = onItemBookClickedObserver;
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
