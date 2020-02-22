package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.robot.domain.entity.testvocab.Level1Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1PresenterImpl<LessonData, Topic, Vocabulary> implements Level1Presenter {

    public PresenterObserver<TestVocabLevel> mNextLevelObserver;

    private Level1ViewWrapper<LessonData, Topic,Vocabulary> mLevel1ViewWrapper;
    private Level1View<LessonData, Topic, Vocabulary> mLevel1View;

    private Level1Model<LessonData, Topic, Vocabulary> mLevel1Model;
    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    private TestVocabLevel mCurrentLevel = TestVocabLevel.LEVEL1_1;
    private List<Vocabulary> mListVocabularyLearning;
    private int mCurrentQuestion = -1;
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
    }

    private void checkAnswer() {
        if (mCurrentAnswerSelected == -1) {
            mLevel1View.notifyMustSelectedAnswer();
            return;
        }

        mLevel1View.showLayoutAnswerResult(mCurrentAnswerSelected == mCurrentQuestion);
        nextVocab();
        // TODO update DB
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
        mLevel1View.setEnableBtnCheckAnswer(true);
    }

    @Override
    public TestVocabLevel getLevel() {
        return mCurrentLevel;
    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        this.mNextLevelObserver = onNextLevelObserver;
    }

    @Override
    public void init() {
        mLevel1ViewWrapper.show();
    }

    @Override
    public void start() {
        requestData();
    }

    private void requestData() {
        mListVocabularyLearning = mLearningVocabModel.getListVocabLearning();
        mTestVocabModel.transformListVocabLearning(mListVocabularyLearning).subscribe(listVocabs -> {
            mListVocabularyLearning = listVocabs;
            // TODO get listvocab learning, list vocab by lesson or topic
            //      => Randomvocab -> answer -> show to screen
            showQuestion();
        });
    }

    private void showQuestion() {
        if (mCurrentQuestion == -1) {
            mCurrentQuestion = 0;
        }

        // TODO get List Answer from model by vocab
        mLevel1View.setEnableBtnCheckAnswer(false);
        mCurrentAnswerSelected = -1;
        List<Vocabulary> listAnswers = mListVocabularyLearning; // for test
        mLevel1View.showQuestion(mCurrentLevel, mListVocabularyLearning.get(mCurrentQuestion), listAnswers);
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
