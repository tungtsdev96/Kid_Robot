package com.android.tupple.robot.domain.presenter.learnvocab;

import com.android.tupple.robot.domain.entity.learnvocab.LearnVocabPresenter;
import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabPresenterImpl<Vocabulary> implements LearnVocabPresenter {

    private final String TAG = "LearningVocabP";

    private LearningVocabView<Vocabulary> mLearningVocabView;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;
    private VocabularyModel<Vocabulary> mVocabularyModel;

    private int mLessonId = -1;
    private int mTopicId = -1;

    private int mCurrentVocabLearning = -1;
    private List<Vocabulary> mListCurrentVocabLearning = new ArrayList<>();

    public LearningVocabPresenterImpl() {
    }

    public void setLearningVocabView(LearningVocabView<Vocabulary> learningVocabView) {
        this.mLearningVocabView = learningVocabView;
        initObservable();
    }

    public void setVocabularyModel(VocabularyModel<Vocabulary> vocabularyModel) {
        this.mVocabularyModel = vocabularyModel;
    }

    public void setLearningVocabModel(LearningVocabModel<Vocabulary> learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    public void setLessonId(int lessonId) {
        this.mLessonId = lessonId;
        this.mTopicId = -1;
    }

    public void setTopicId(int topicId) {
        this.mTopicId = topicId;
        this.mLessonId = -1;
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

        if (mLessonId > -1) {
            mVocabularyModel.getListVocabularyByLessonId(mLessonId).subscribe(this::handleListVocab);
            return;
        }

        if (mTopicId > -1) {
            mVocabularyModel.getListVocabularyByTopicId(mTopicId).subscribe(this::handleListVocab);
            return;
        }

        CLog.printD(TAG, "Haven't set LessonId or TopicId");
    }

    private void handleListVocab(List<Vocabulary> listVocabularies) {
        if (listVocabularies == null || listVocabularies.isEmpty()) {
            CLog.printD(TAG, "ListVocab is empty");
            return;
        }

        mListCurrentVocabLearning.clear();
        mListCurrentVocabLearning.addAll(listVocabularies);
        mCurrentVocabLearning = 0;
        mLearningVocabView.setCurrentVocabLearning(mListCurrentVocabLearning.get(mCurrentVocabLearning));
    }

    private void initObservable() {
        mLearningVocabView.getNextButtonClickedObservable().subscribe(this::nextVocab);
        mLearningVocabView.getPreviousButtonClickedObservable().subscribe(this::previousVocab);
    }

    private void previousVocab() {
        if (mCurrentVocabLearning == 0) {
            return;
        }

        mCurrentVocabLearning--;
        mLearningVocabView.setCurrentVocabLearning(mListCurrentVocabLearning.get(mCurrentVocabLearning));
    }

    private void nextVocab() {
        if (mCurrentVocabLearning == mListCurrentVocabLearning.size() - 1) {
            // TODO onLearningDone
            return;
        }

        mCurrentVocabLearning++;
        mLearningVocabView.setCurrentVocabLearning(mListCurrentVocabLearning.get(mCurrentVocabLearning));
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
