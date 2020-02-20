package com.android.tupple.robot.domain.presenter.testvocab.level3;

import com.android.tupple.robot.domain.entity.testvocab.Level3Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3PresenterImpl<LessonData, Topic, Vocabulary> implements Level3Presenter {

    private Level3ViewWrapper<LessonData, Topic, Vocabulary> mLevel3ViewWrapper;
    private Level3View<LessonData, Topic, Vocabulary> mLevel3View;

    private Level3Model<LessonData, Topic, Vocabulary> mLevel3Model;
    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private LearningVocabModel<Vocabulary> mLearningVocabModel;

    public void setLevel3Model(Level3Model<LessonData, Topic, Vocabulary> level3Model) {
        this.mLevel3Model = level3Model;
    }

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = testVocabModel;
    }

    public void setLearningVocabModel(LearningVocabModel<Vocabulary> learningVocabModel) {
        this.mLearningVocabModel = learningVocabModel;
    }

    public void setLevel3ViewWrapper(Level3ViewWrapper<LessonData, Topic, Vocabulary> level3ViewWrapper) {
        this.mLevel3ViewWrapper = level3ViewWrapper;
        this.mLevel3ViewWrapper.getViewCreatedObservable().subscribe(this::setLevel3View);
    }

    public void setLevel3View(Level3View<LessonData, Topic, Vocabulary> level3View) {
        this.mLevel3View = level3View;
        initObservable();
    }

    private void initObservable() {

    }

    @Override
    public TestVocabLevel getLevel() {
        return TestVocabLevel.LEVEL3_1;
    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {
        // TODO show dialog Result
    }

    @Override
    public void init() {
        mLevel3ViewWrapper.show();
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
