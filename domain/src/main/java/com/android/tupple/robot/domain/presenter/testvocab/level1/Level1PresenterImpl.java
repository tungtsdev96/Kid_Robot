package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.robot.domain.entity.testvocab.Level1Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1PresenterImpl<LessonData, Topic, Vocabulary> implements Level1Presenter {

    private Level1ViewWrapper<LessonData, Topic,Vocabulary> mLevel1ViewWrapper;
    private Level1View<LessonData, Topic, Vocabulary> mLevel1View;

    private Level1Model<LessonData, Topic, Vocabulary> mLevel1Model;
    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;

    private Vocabulary mCurrentVocabulary;

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = testVocabModel;
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
        // TODO innit observable
//        mLevel1View.getAnswerChooseObservable().subscribe(this::chooseAnswer)
    }

    private void chooseAnswer(boolean isAnswer) {
        // TODO update toDB
    }

    @Override
    public TestVocabLevel getLevel() {
        return TestVocabLevel.LEVEL1_1;
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
        mTestVocabModel.getAllVocabLearning().subscribe(this::showDataToView);
    }

    private void showDataToView(List<Vocabulary> vocabularies) {
        if (mLevel1View != null) {
            mLevel1View.setData(vocabularies);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void finish() {

    }

}
