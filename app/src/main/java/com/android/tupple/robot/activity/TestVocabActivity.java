package com.android.tupple.robot.activity;

import android.os.Bundle;
import android.widget.Toast;

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
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3PresenterImpl;
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3ViewWrapper;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemPresenterImpl;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemViewWrapper;
import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultPresenterImpl;
import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultView;
import com.android.tupple.robot.view.testvocab.TestVocabViewFactory;
import com.android.tupple.robot.view.testvocab.level3.item.Level3ItemViewWrapperFactory;
import com.android.tupple.robot.view.testvocab.result.AnswerResultViewFactory;

import java.util.List;

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
        injectResultAnswer();
        injectLevel1();
        injectLevel2();
        injectLevel3();
    }

    private void injectResultAnswer() {
        AnswerResultPresenterImpl answerResultPresenter = new AnswerResultPresenterImpl();
        AnswerResultView answerResultView = AnswerResultViewFactory.newAnswerResultView(this);

        answerResultPresenter.setAnswerResultView(answerResultView);
        mTestVocab.setAnswerResultPresenter(answerResultPresenter);
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

    public void injectLevel3() {
        Level3PresenterImpl<LessonData, Topic, Vocabulary> level3Presenter = new Level3PresenterImpl<>();
        Level3ViewWrapper<LessonData, Topic, Vocabulary> level3ViewWrapper = TestVocabViewFactory.newLevel3ViewWrapper(getSupportFragmentManager());
        TestVocabModel<LessonData, Topic, Vocabulary> testVocabModel = TestVocabModelFactory.newTestVocabModel(this);
        LearningVocabModel<Vocabulary> learningVocabModel = LearningVocabModelFactory.newLearningVocabModel(this);
//        Level2Model<LessonData, Topic, Vocabulary> level2Model = TestVocabModelFactory.newLevel2Model(this);

        level3Presenter.setLevel3ViewWrapper(level3ViewWrapper);
        level3Presenter.setLearningVocabModel(learningVocabModel);
        level3Presenter.setTestVocabModel(testVocabModel);
        level3Presenter.setListLearningVocabHandler(vocabularies -> this.handleListLearningVocab(vocabularies, level3Presenter));
//        level3Presenter.setLevel3Model(level3Model);
        level3Presenter.setOnNextLevelObserver(level -> Toast.makeText(this, "Done test", Toast.LENGTH_SHORT).show());
        mTestVocab.setLevel3Presenter(level3Presenter);
    }

    private void handleListLearningVocab(List<Vocabulary> vocabularies, Level3PresenterImpl level3Presenter) {
        // inject level3 item presenter
        for (Vocabulary vocabulary: vocabularies) {
            Level3ItemPresenterImpl<Vocabulary> level3ItemPresenter = new Level3ItemPresenterImpl<>(vocabulary);
            Level3ItemViewWrapper<Vocabulary> level3ItemViewWrapper = Level3ItemViewWrapperFactory.newLevel3ItemViewWrapper(this, vocabulary.getVocabId());
            level3ItemPresenter.setLevel3ItemViewWrapper(level3ItemViewWrapper);

            level3Presenter.addLevel3ItemPresenter(level3ItemPresenter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestVocab.start();
    }
}
