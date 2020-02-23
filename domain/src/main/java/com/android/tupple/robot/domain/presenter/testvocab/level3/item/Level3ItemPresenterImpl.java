package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import com.android.tupple.robot.domain.entity.testvocab.Level3ItemPresenter;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemPresenterImpl<Vocabulary> implements Level3ItemPresenter {

    private Level3ItemViewWrapper<Vocabulary> mLevel3ItemViewWrapper;
    private Level3ItemView<Vocabulary> mLevel3ItemView;

    private Vocabulary mVocabulary;

    public Level3ItemPresenterImpl(Vocabulary vocabulary) {
        this.mVocabulary = vocabulary;
    }

    public void setLevel3ItemViewWrapper(Level3ItemViewWrapper<Vocabulary> level3ItemViewWrapper) {
        this.mLevel3ItemViewWrapper = level3ItemViewWrapper;
        this.mLevel3ItemViewWrapper.getViewCreatedObservable().subscribe(this::onViewCreated);
    }

    private void onViewCreated(Level3ItemView<Vocabulary> level3ItemView) {
        this.mLevel3ItemView = level3ItemView;
        initObservable();

        doOnStart();
    }

    private void initObservable() {

    }

    @Override
    public void init() {

    }

    private void doOnStart() {
        mLevel3ItemView.setVocabulary(mVocabulary);
    }

    @Override
    public void stop() {
        if (mLevel3ItemViewWrapper != null) {
            mLevel3ItemViewWrapper.hide();
        }
    }


}
