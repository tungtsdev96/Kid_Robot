package com.android.tupple.robot.domain.presenter.testvocab.level2;

import com.android.tupple.robot.domain.entity.testvocab.Level2Presenter;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level2PresenterImpl<LessonData, Topic, Vocabulary> implements Level2Presenter {

    private Level2ViewWrapper<LessonData, Topic, Vocabulary> mLevel2ViewWrapper;
    private Level2View<LessonData, Topic, Vocabulary> mLevel2View;

    private TestVocabModel<LessonData, Topic, Vocabulary> mTestVocabModel;
    private Level2Model<LessonData, Topic, Vocabulary> mLevel2Model;

    public void setLevel2ViewWrapper(Level2ViewWrapper<LessonData, Topic, Vocabulary> level2ViewWrapper) {
        this.mLevel2ViewWrapper = level2ViewWrapper;
        mLevel2ViewWrapper.getViewCreatedObservable().subscribe(this::setLevel2View);
    }

    public void setLevel2View(Level2View<LessonData, Topic, Vocabulary> level2View) {
        this.mLevel2View = level2View;
        // TODO init Observable
    }

    public void setTestVocabModel(TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel) {
        this.mTestVocabModel = mTestVocabModel;
    }

    public void setmLevel2Model(Level2Model<LessonData, Topic, Vocabulary> mLevel2Model) {
        this.mLevel2Model = mLevel2Model;
    }

    @Override
    public TestVocabLevel getLevel() {
        return null;
    }

    @Override
    public void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver) {

    }

    @Override
    public void init() {
        this.mLevel2ViewWrapper.show();
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
