package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.robot.domain.entity.menumain.EnglishTopicPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;

/**
 * Created by tungts on 2020-01-12.
 */

public class EnglishTopicPresenterImpl<Topic> implements EnglishTopicPresenter {

    private EnglishTopicViewWrapper<Topic> mEnglishTopicViewWrapper;
    private EnglishTopicView<Topic> mEnglishTopicView;
    private EnglishTopicModel<Topic> mEnglishTopicModel;

    public EnglishTopicPresenterImpl() {
    }

    public void setEnglishTopicViewWrapper(EnglishTopicViewWrapper<Topic> englishTopicViewWrapper) {
        this.mEnglishTopicViewWrapper = englishTopicViewWrapper;
        mEnglishTopicViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
        // TODO innit Observerable
    }

    public void setEnglishBookModel(EnglishTopicModel<Topic> englishTopicView) {
        this.mEnglishTopicModel = englishTopicView;
    }

    @Override
    public void init() {
        mEnglishTopicViewWrapper.show();
    }

    private void onViewCreated(EnglishTopicView<Topic> englishTopicView) {
        this.mEnglishTopicView = englishTopicView;
        // TODO innit Observerable
    }

    @Override
    public void start() {

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
