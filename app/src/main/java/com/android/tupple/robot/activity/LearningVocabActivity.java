package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.data.model.learningvocab.LearningVocabModelFactory;
import com.android.tupple.robot.data.model.vocabulary.VocabularyModelFactory;
import com.android.tupple.robot.domain.entity.learnvocab.LearnVocab;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;
import com.android.tupple.robot.domain.presenter.data.LearningVocabModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabPresenterImpl;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.view.learningvocab.LearningVocabViewFactory;

import static com.android.tupple.robot.utils.constant.LearnVocabConstant.TestVocab.EXTRA_IS_TOPIC;
import static com.android.tupple.robot.utils.constant.LessonConstant.EXTRA_LESSON;
import static com.android.tupple.robot.utils.constant.TopicConstant.EXTRA_TOPIC;

/**
 * Created by tungts on 2020-01-18.
 */

public class LearningVocabActivity extends BaseActivity {

    private ActivityLauncher mActivityLauncher;
    private LearnVocab mLearnVocab;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_learning_vocab;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }

        initFirstBatch();
        inject(bundle);
    }

    private void initFirstBatch() {
        mActivityLauncher = new ActivityLauncher(this);
        mLearnVocab = new LearnVocab();
    }

    private void inject(Bundle bundle) {
        LearningVocabPresenterImpl<Vocabulary> learningVocabPresenter = new LearningVocabPresenterImpl<>();
        LearningVocabView<Vocabulary> learningVocabView = LearningVocabViewFactory.newLearningVocabView(this);
        VocabularyModel<Vocabulary> vocabularyModel = VocabularyModelFactory.newVocabularyModel(this);
        LearningVocabModel<Vocabulary> learningVocabModel = LearningVocabModelFactory.newLearningVocabModel(this);

        learningVocabPresenter.setLearningVocabView(learningVocabView);
        learningVocabPresenter.setVocabularyModel(vocabularyModel);
        learningVocabPresenter.setLearningVocabModel(learningVocabModel);
        setLessonOrTopic(bundle, learningVocabPresenter);

        initObserver(learningVocabPresenter);

        mLearnVocab.setLearnVocabPresenter(learningVocabPresenter);
        mLearnVocab.init();
    }

    private void setLessonOrTopic(Bundle bundle, LearningVocabPresenterImpl<Vocabulary> learningVocabPresenter) {
        boolean isLesson = bundle.getBoolean(LearnVocabConstant.TestVocab.EXTRA_IS_LESSON, false);
        if (isLesson) {
            LessonData lessonData = bundle.getParcelable(EXTRA_LESSON);
            if (lessonData != null) {
                learningVocabPresenter.setLessonId(lessonData.getLessonId());
            }
        }

        boolean isTopic = bundle.getBoolean(EXTRA_IS_TOPIC, false);
        if (isTopic) {
            Topic topic = bundle.getParcelable(EXTRA_TOPIC);
            if (topic != null) {
                learningVocabPresenter.setTopicId(topic.getTopicId());
            }
        }
    }

    private void initObserver(LearningVocabPresenterImpl<Vocabulary> learningVocabPresenter) {
        learningVocabPresenter.setOnCloseButtonHandler(this::onBackPressed);
        learningVocabPresenter.setOnDoneLearningVocabListener(() -> {
            mActivityLauncher.launchTestVocabActivity();
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLearnVocab.finish();
    }
}