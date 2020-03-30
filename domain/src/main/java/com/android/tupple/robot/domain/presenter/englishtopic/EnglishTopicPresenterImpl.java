package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.robot.domain.entity.englishtopic.MyEnglishTopicPresenter;
import com.android.tupple.robot.domain.entity.menumain.EnglishTopicPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-01-12.
 */

public class EnglishTopicPresenterImpl<Topic> implements MyEnglishTopicPresenter {

    private EnglishTopicView<Topic> mEnglishTopicView;
    private EnglishTopicModel<Topic> mEnglishTopicModel;

    private PresenterObserver<Topic> mItemTopicClickedObserver;

    private boolean mIsLoadData = false;

    public EnglishTopicPresenterImpl() {
    }


    public void setEnglishBookModel(EnglishTopicModel<Topic> englishTopicView) {
        this.mEnglishTopicModel = englishTopicView;
    }

    public void setmEnglishTopicView(EnglishTopicView<Topic> englishTopicView){
        this.mEnglishTopicView = englishTopicView;
        initObservable();
    }

    @Override
    public void init() {
        mEnglishTopicView.initLayout();
        mIsLoadData = false;
        start();
    }


    private void initObservable() {
        mEnglishTopicView.getItemTopicClickedObservable().subscribe(this::handleItemTopicClicked);
        mEnglishTopicView.getButtonCloseClickedObservable().subscribe(this::closeTopicActivity);
    }

    private void closeTopicActivity() {
        mEnglishTopicView.closeActivity();
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



}
