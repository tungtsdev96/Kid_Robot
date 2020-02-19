package com.android.tupple.robot.activity;

import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.data.model.learningvocab.LearningVocabModelFactory;
import com.android.tupple.robot.data.model.testvocab.TestVocabModelFactory;
import com.android.tupple.robot.domain.entity.testvocab.TestVocab;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1Model;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1PresenterImpl;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1ViewWrapper;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2Model;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2PresenterImpl;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2ViewWrapper;
import com.android.tupple.robot.view.testvocab.TestVocabViewFactory;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class TestVocabActivity extends BaseActivity {

    private TestVocab mTestVocab;
    private ActivityLauncher mActivityLauncher;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_test_vocabulary;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        mTestVocab = new TestVocab();
        mActivityLauncher = new ActivityLauncher(this);

        inject();
        mTestVocab.init();
    }

    private void inject() {
        injectLevel1();
        injectLevel2();
    }

    private void injectLevel1() {
        Level1PresenterImpl<LessonData, Topic, Vocabulary> level1Presenter = new Level1PresenterImpl<>();
        Level1ViewWrapper<LessonData, Topic, Vocabulary> level1ViewWrapper = TestVocabViewFactory.newLevel1ViewWrapper(getSupportFragmentManager());
        TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel = TestVocabModelFactory.newTestVocabModel(this);
        LearningVocabModel<Vocabulary> learningVocabModel = LearningVocabModelFactory.newLearningVocabModel(this);
        Level1Model<LessonData, Topic, Vocabulary> level1Model = TestVocabModelFactory.newLevel1Model(this);

        level1Presenter.setLevel1ViewWrapper(level1ViewWrapper);
        level1Presenter.setTestVocabModel(testVocabModel);
        level1Presenter.setLearningVocabModel(learningVocabModel);
        level1Presenter.setLevel1Model(level1Model);
        mTestVocab.setLevel1Presenter(level1Presenter);
        mTestVocab.setCurrentPresenter(TestVocabLevel.LEVEL1_1);
    }

    private void injectLevel2() {
        Level2PresenterImpl<LessonData, Topic, Vocabulary> level2Presenter = new Level2PresenterImpl<>();
        Level2ViewWrapper<LessonData, Topic, Vocabulary> level2ViewWrapper = TestVocabViewFactory.newLevel2ViewWrapper(getSupportFragmentManager());
        TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel = TestVocabModelFactory.newTestVocabModel(this);
        LearningVocabModel<Vocabulary> learningVocabModel = LearningVocabModelFactory.newLearningVocabModel(this);
        Level2Model<LessonData, Topic, Vocabulary> level2Model = TestVocabModelFactory.newLevel2Model(this);

        level2Presenter.setLevel2ViewWrapper(level2ViewWrapper);
        level2Presenter.setLearningVocabModel(learningVocabModel);
        level2Presenter.setTestVocabModel(testVocabModel);
        level2Presenter.setLevel2Model(level2Model);
        mTestVocab.setLevel2Presenter(level2Presenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestVocab.start();
    }
}
