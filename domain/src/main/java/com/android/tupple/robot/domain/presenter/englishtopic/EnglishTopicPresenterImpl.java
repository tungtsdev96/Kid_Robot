package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.robot.domain.entity.menumain.EnglishTopicPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-01-12.
 */

public class EnglishTopicPresenterImpl<Topic> implements EnglishTopicPresenter {

    private EnglishTopicViewWrapper<Topic> mEnglishTopicViewWrapper;
    private EnglishTopicView<Topic> mEnglishTopicView;
    private EnglishTopicModel<Topic> mEnglishTopicModel;

    private PresenterObserver<Topic> mItemTopicClickedObserver;

    private boolean mIsLoadData = false;

    public EnglishTopicPresenterImpl() {
    }

    public void setEnglishTopicViewWrapper(EnglishTopicViewWrapper<Topic> englishTopicViewWrapper) {
        this.mEnglishTopicViewWrapper = englishTopicViewWrapper;
        mEnglishTopicViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    public void setEnglishBookModel(EnglishTopicModel<Topic> englishTopicView) {
        this.mEnglishTopicModel = englishTopicView;
    }

    @Override
    public void init() {
        mEnglishTopicViewWrapper.show();
        mIsLoadData = false;
    }

    private void onViewCreated(EnglishTopicView<Topic> englishTopicView) {
        this.mEnglishTopicView = englishTopicView;
        initObservable();
        start();
    }

    private void initObservable() {
        mEnglishTopicView.getItemTopicClickedObservable().subscribe(this::handleItemTopicClicked);
    }

    private void handleItemTopicClicked(Topic topic) {
        if (mItemTopicClickedObserver != null) {
            mItemTopicClickedObserver.onComplete(topic);
        }
    }

    @Override
    public void start() {
        if (!mIsLoadData) {
            requestTopicData();
            mIsLoadData = true;
        }
    }

    private void requestTopicData() {
        mEnglishTopicModel.getAllTopic().subscribe(mEnglishTopicView::setListTopic);
    }

    public void setOnItemBookClickedObserver(PresenterObserver<Topic> onItemTopicClickedObserver) {
        this.mItemTopicClickedObserver = onItemTopicClickedObserver;
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ENGLISH_TOPIC;
    }

}
