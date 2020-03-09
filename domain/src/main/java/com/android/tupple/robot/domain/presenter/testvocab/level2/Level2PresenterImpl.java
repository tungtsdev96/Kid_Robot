package com.android.tupple.robot.domain.presenter.testvocab.level2;

import com.android.tupple.robot.domain.entity.testvocab.Level2Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.testvocab.TestVocabModel;
import com.android.tupple.robot.domain.presenter.data.LearningVocabModel;
import com.android.tupple.robot.domain.presenter.testvocab.ResultAnswerHandler;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level2PresenterImpl<LessonData, Topic, Vocabulary> implements Level2Presenter {

    private Level2ViewWrapper<LessonData, Topic, Vocabulary> mLevel2ViewWrapper;
    private Level2View<LessonData, Topic, Vocabulary> mLevel2View;

    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private Level2Model<LessonData, Topic, Vocabulary> mLevel2Model;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    private TestVocabLevel mCurrentLevel = TestVocabLevel.LEVEL2_1;
    private List<Vocabulary> mListVocabularyLearning;
    private int mCurrentQuestion = -1;

    private boolean initialize = false;

    public Level2PresenterImpl() {
    }

    public void setLevel2ViewWrapper(Level2ViewWrapper<LessonData, Topic, Vocabulary> level2ViewWrapper) {
        this.mLevel2ViewWrapper = level2ViewWrapper;
        mLevel2ViewWrapper.getViewCreatedObservable().subscribe(this::setLevel2View);
    }

    public void setLevel2View(Level2View<LessonData, Topic, Vocabulary> level2View) {
        this.mLevel2View = level2View;
        // TODO start Observable

        start();
    }

    public void setLearningVocabModel(LearningVocabModel<Vocabulary> learningVocabModel) {
        mLearningVocabModel = learningVocabModel;
    }

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = testVocabModel;
    }

    public void setLevel2Model(Level2Model<LessonData, Topic, Vocabulary> mLevel2Model) {
        this.mLevel2Model = mLevel2Model;
    }

    @Override
    public TestVocabLevel getLevel() {
        return null;
    }

    @Override
    public void nextQuestion() {

    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        // TODO onNext level
    }

    @Override
    public void setOnResultAnswerHandler(ResultAnswerHandler onResultAnswerHandler) {

    }

    @Override
    public void init() {
        this.mLevel2ViewWrapper.show();
    }

    @Override
    public void start() {
        requestData();
    }

    private void requestData() {
        if (!initialize) {
            mListVocabularyLearning = mLearningVocabModel.getListVocabLearning();
            mTestVocabModel.transformListVocabLearning(mListVocabularyLearning).subscribe(listVocabs -> {
                mListVocabularyLearning = listVocabs;
                // TODO get listvocab learning, list vocab by lesson or topic
                //      => Randomvocab -> answer -> show to screen
                showQuestion();
            });

            initialize = true;
        }
    }

    private void showQuestion() {
        if (mCurrentQuestion == -1) {
            mCurrentQuestion = 0;
        }

        // TODO get List Answer from model by vocab
        char[] content = new char[]{'t', 'u', '_', '_', 't', 's'};
        mLevel2View.showQuestion(content);

        char[] answer = new char[]{'t', 'u', 't', 's'};
        mLevel2View.showAnswer(answer);
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
