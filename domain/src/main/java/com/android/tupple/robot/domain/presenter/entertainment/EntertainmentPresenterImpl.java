package com.android.tupple.robot.domain.presenter.entertainment;

import com.android.tupple.robot.domain.entity.menumain.EntertainmentPresenter;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;

public class EntertainmentPresenterImpl implements EntertainmentPresenter {

    private EntertainmentViewWrapper mEntertainmentViewWrapper;
    private EntertainmentView mEntertainmentView;
    private EntertainmentModel mEntertainmentModel;

    public EntertainmentPresenterImpl() {

    }

    public void setEntertainmentViewWrapper(EntertainmentViewWrapper entertainmentViewWrapper) {
        this.mEntertainmentViewWrapper = entertainmentViewWrapper;
        mEntertainmentViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
        // TODO innit Observerable
    }

    @Override
    public MenuType getMenuType() {
        return MenuType.ENTERTAINMENT;
    }

    public void setEntertainmentModel(EntertainmentModel entertainmentModel) {
        this.mEntertainmentModel = entertainmentModel;
    }

    @Override
    public void init() {
        mEntertainmentViewWrapper.show();
    }

    private void onViewCreated(EntertainmentView entertainmentView) {
        this.mEntertainmentView = entertainmentView;
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
}
