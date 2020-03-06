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

    public interface CloseButtonHandler {
        void onClose();
    }

    public interface DoneLearningVocabListener {
        void onComplete();
    }

    private final String TAG = "LearningVocabP";

    private LearningVocabView<Vocabulary> mLearningVocabView;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;
    private VocabularyModel<Vocabulary> mVocabularyModel;

    private int mLessonId = -1;
    private int mTopicId = -1;

    private int mCurrentVocabLearning = -1;
    private List<Vocabulary> mListCurrentVocabLearning = new ArrayList<>();

    private CloseButtonHandler mOnCloseButtonHandler;
    private DoneLearningVocabListener mOnDoneLearningVocabListener;

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
        start();
    }

    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        if (mLessonId > -1) {
            mLearningVocabModel.getListVocabLearningByLessonId(mLessonId).subscribe(this::handleListVocab);
            return;
        }

        if (mTopicId > -1) {
            mLearningVocabModel.makeListVocabLearningByTopicId(mTopicId).subscribe(this::handleListVocab);
            return;
        }

        CLog.printD(TAG, "Haven't set LessonId or TopicId");
    }

    private void handleListVocab(List<Vocabulary> listVocabularies) {
        if (listVocabularies == null || listVocabularies.isEmpty()) {
            CLog.printD(TAG, "ListVocab is empty");
            return;
        }

        CLog.printD(TAG, "ListVocab size: " + listVocabularies.size());
        mListCurrentVocabLearning.clear();
        mListCurrentVocabLearning.addAll(listVocabularies);
        mCurrentVocabLearning = 0;
        mLearningVocabView.enablePreviousButton(false);
        mLearningVocabView.setListVocabLearning(mListCurrentVocabLearning);
        mLearningVocabView.setTitleHeader(1, mListCurrentVocabLearning.size());
    }

    private void initObservable() {
        mLearningVocabView.getCloseButtonClickedObservable().subscribe(this::handleCloseButton);
        mLearningVocabView.getNextButtonClickedObservable().subscribe(this::handleNextButton);
        mLearningVocabView.getPreviousButtonClickedObservable().subscribe(this::handlePreviousButton);
    }

    private void handleCloseButton() {
        if (mOnCloseButtonHandler != null) {
            mOnCloseButtonHandler.onClose();
        }
    }

    private void handlePreviousButton() {
        if (mCurrentVocabLearning == 0) {
            return;
        }

        mLearningVocabView.enablePreviousButton(mCurrentVocabLearning != 1);
        mCurrentVocabLearning--;
        mLearningVocabView.setCurrentSlide(mCurrentVocabLearning);
        if (mCurrentVocabLearning % 3 == 2) {
            mLearningVocabView.setTitleHeader(mCurrentVocabLearning / 3 + 1, mListCurrentVocabLearning.size());
        }
    }

    private void handleNextButton() {
        if (mCurrentVocabLearning == mListCurrentVocabLearning.size() * 3 - 1) {
            if (mOnDoneLearningVocabListener != null) {
                mOnDoneLearningVocabListener.onComplete();
            }
            return;
        }

        mCurrentVocabLearning++;
        mLearningVocabView.enablePreviousButton(true);
        mLearningVocabView.setCurrentSlide(mCurrentVocabLearning);
        if (mCurrentVocabLearning % 3 == 0) {
            mLearningVocabView.setTitleHeader(mCurrentVocabLearning / 3 + 1, mListCurrentVocabLearning.size());
        }
    }

    public CloseButtonHandler getOnCloseButtonHandler() {
        return mOnCloseButtonHandler;
    }

    public void setOnCloseButtonHandler(CloseButtonHandler onButtonCloseHandler) {
        this.mOnCloseButtonHandler = onButtonCloseHandler;
    }

    public DoneLearningVocabListener getOnLearningVocabListener() {
        return mOnDoneLearningVocabListener;
    }

    public void setOnDoneLearningVocabListener(DoneLearningVocabListener onLearningVocabListener) {
        this.mOnDoneLearningVocabListener = onLearningVocabListener;
    }

    @Override
    public void stop() {
    }

    @Override
    public void finish() {
        mLearningVocabModel.cancel();
    }


}
