package com.android.tupple.robot.domain.presenter.testvocab.level1;

import android.util.Log;

import com.android.tupple.robot.domain.entity.testvocab.Level1Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.testvocab.TestVocabModel;
import com.android.tupple.robot.domain.presenter.data.LearningVocabModel;
import com.android.tupple.robot.domain.presenter.testvocab.ResultAnswerHandler;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1PresenterImpl<LessonData, Topic, Vocabulary> implements Level1Presenter {

    private final String TAG = "Level1PresenterImpl";

    private PresenterObserver<TestVocabLevel> mNextLevelObserver;
    private ResultAnswerHandler mOnResultAnswerHandler;

    private Level1ViewWrapper<LessonData, Topic,Vocabulary> mLevel1ViewWrapper;
    private Level1View<LessonData, Topic, Vocabulary> mLevel1View;

    private Level1Model<LessonData, Topic, Vocabulary> mLevel1Model;
    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    private boolean isNeedLoadData;
    private TestVocabLevel mCurrentLevel = TestVocabLevel.LEVEL1_1;
    private List<Vocabulary> mListVocabularyLearning;
    private int mCurrentQuestion = -1;
    private int mAnswerRight = -1;
    private int mCurrentAnswerSelected = -1;

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = testVocabModel;
    }

    public void setLearningVocabModel(LearningVocabModel<Vocabulary> learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    public void setLevel1Model(Level1Model<LessonData, Topic, Vocabulary> level1Model){
        this.mLevel1Model = level1Model;
    }

    public void setLevel1ViewWrapper(Level1ViewWrapper<LessonData, Topic, Vocabulary> level1ViewWrapper) {
        this.mLevel1ViewWrapper = level1ViewWrapper;
        this.mLevel1ViewWrapper.getViewCreatedObservable().subscribe(this::setLevel1View);
    }

    public void setLevel1View(Level1View<LessonData, Topic, Vocabulary> level1View) {
        this.mLevel1View = level1View;
        initObservable();
    }

    private void initObservable() {
        mLevel1View.getAnswerSelectedObservable().subscribe(this::setAnswerSelected);
        mLevel1View.getBtnCheckAnswerClickedObservable().subscribe(this::checkAnswer);
        mLevel1View.getBtnPronounceVocabClickedObservable().subscribe(this::playAudioVocab);
    }

    private void playAudioVocab() {
        Vocabulary question = mListVocabularyLearning.get(mCurrentQuestion);
        mLevel1View.playAudioVocab(question);
    }

    private void checkAnswer(boolean isCheck) {
        if (mCurrentAnswerSelected == -1) {
            mLevel1View.notifyMustSelectedAnswer();
            return;
        }

        boolean isRight = mCurrentAnswerSelected == mAnswerRight;

        if (!isCheck) {
            mTestVocabModel.updateQuestionForVocab(isRight, mListVocabularyLearning.get(mCurrentQuestion));
            nextQuestion();
            if (mOnResultAnswerHandler != null) {
                mOnResultAnswerHandler.onResult(isRight, 1);
            }
            return;
        }

        mLevel1View.showLayoutAnswerResult(isRight, mAnswerRight);
    }

    private void nextVocab() {
        if (mCurrentQuestion == mListVocabularyLearning.size() - 1) {
            mCurrentLevel = mCurrentLevel == TestVocabLevel.LEVEL1_1 ? TestVocabLevel.LEVEL1_2 : TestVocabLevel.LEVEL3_1;
            nextLevel();
            return;
        }

        mCurrentQuestion++;
        showQuestion();
    }

    private void nextLevel() {
        if (mCurrentLevel == TestVocabLevel.LEVEL1_2) {
            mLevel1View.setEnableBtnCheckAnswer(false);
            mCurrentQuestion = -1;
            showQuestion();
            return;
        }

        if (mNextLevelObserver != null) {
            mNextLevelObserver.onComplete(mCurrentLevel);
        }
    }

    private void setAnswerSelected(int answerSelected) {
        mCurrentAnswerSelected = answerSelected;
        mLevel1View.setAnswerSelected(answerSelected);
        mLevel1View.setEnableBtnCheckAnswer(true);
    }

    @Override
    public TestVocabLevel getLevel() {
        return mCurrentLevel;
    }

    @Override
    public void nextQuestion() {
        nextVocab();
    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        this.mNextLevelObserver = onNextLevelObserver;
    }

    @Override
    public void setOnResultAnswerHandler(ResultAnswerHandler onResultAnswerHandler) {
        this.mOnResultAnswerHandler = onResultAnswerHandler;
    }

    @Override
    public void init() {
        mLevel1ViewWrapper.show();
        isNeedLoadData = true;
    }

    @Override
    public void start() {
        if (isNeedLoadData) {
            requestData();
        }
        isNeedLoadData = false;
    }

    private void requestData() {
        mListVocabularyLearning = mLearningVocabModel.getListVocabLearning();
        mTestVocabModel.transformListVocabLearning(mListVocabularyLearning).subscribe(listVocabs -> {
            mListVocabularyLearning = listVocabs;
            showQuestion();
        });
    }

    private void showQuestion() {
        if (mCurrentQuestion == -1) {
            mCurrentQuestion = 0;
        }

        Vocabulary question = mListVocabularyLearning.get(mCurrentQuestion);
        mTestVocabModel.makeListAnswerFromVocab(mListVocabularyLearning, question).subscribe((listAnswers) -> {
            Log.d(TAG, "show list answer " + listAnswers);
            mLevel1View.setEnableBtnCheckAnswer(false);
            mCurrentAnswerSelected = -1;
            mAnswerRight = mLevel1Model.getAnswerRight(listAnswers, question);
            mLevel1View.showQuestion(mCurrentLevel, question, listAnswers);
        });
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {
        if (mTestVocabModel != null) {
            mTestVocabModel.destroy();
        }
    }

}
