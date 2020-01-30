package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.data.model.vocabulary.VocabularyModelFactory;
import com.android.tupple.robot.domain.entity.learnvocab.LearnVocab;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabPresenterImpl;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabView;
import com.android.tupple.robot.view.learningvocab.LearningVocabViewFactory;

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

        // TODO parse intent
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
        VocabularyModel<Vocabulary> learningVocabModel = VocabularyModelFactory.newVocabularyModel(this);

        learningVocabPresenter.setLearningVocabView(learningVocabView);
        learningVocabPresenter.setVocabularyModel(learningVocabModel);
        learningVocabPresenter.setLessonId(1);

        initObserver(learningVocabPresenter);

        mLearnVocab.setLearnVocabPresenter(learningVocabPresenter);
        mLearnVocab.init();
    }

    private void initObserver(LearningVocabPresenterImpl<Vocabulary> learningVocabPresenter) {
        learningVocabPresenter.setOnDoneLearningVocabListener(listVocabularies -> {
//            mActivityLauncher.launchTestVocabActivity(listVocabularies, new LessonData());
            // TODO TungTS
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLearnVocab.start();
    }
}
