package com.android.tupple.robot.domain.presenter.learnvocab;

import com.android.tupple.robot.domain.entity.learnvocab.LearnVocabPresenter;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabPresenterImpl<LessonData, Vocabulary> implements LearnVocabPresenter {

    private LearningVocabView<LessonData, Vocabulary> mLearningVocabView;
    private LearningVocabModel<LessonData, Vocabulary> mLearningVocabModel;

    public LearningVocabPresenterImpl() {
    }

    public void setLearningVocabView(LearningVocabView<LessonData, Vocabulary> learningVocabView) {
        this.mLearningVocabView = learningVocabView;
    }

    public void setLearningVocabModel(LearningVocabModel<LessonData, Vocabulary> learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    @Override
    public void init() {
        mLearningVocabView.initLayout();
    }

    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        // TODO loadData went start
        //      1, load list vocabulary from DB (haven't yet learn, wrong many time)
        //      2, update to view
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }
}
