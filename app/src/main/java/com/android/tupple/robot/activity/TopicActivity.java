package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.model.english.EnglishModelFactory;
import com.android.tupple.robot.domain.entity.englishtopic.EnglishTopic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.view.englishtopic.EnglishTopicViewFactory;

public class TopicActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private EnglishTopic englishTopic;


    @Override
    protected int getLayoutContent() {
        return R.layout.activity_english_topic;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        injectFirstBatch();
        inject(bundle);
    }

    private void inject(Bundle bundle) {
        EnglishTopicPresenterImpl<Topic> englishTopicPresenter = new EnglishTopicPresenterImpl<>();
        EnglishTopicModel<Topic> englishTopicModel = EnglishModelFactory.newEnglishTopicModel(this);
        EnglishTopicView<Topic> englishTopicView = EnglishTopicViewFactory.newEnglishTopicView(this,bundle);
        englishTopicPresenter.setEnglishBookModel(englishTopicModel);
        englishTopicPresenter.setmEnglishTopicView(englishTopicView);

        englishTopicPresenter.setOnCloseButtonHandler(this::finish);
        // innit Observerable
        englishTopicPresenter.setOnItemBookClickedObserver(activityLauncher::launchLearningVocabActivity);
        englishTopic.setEnglishTopicPresenter(englishTopicPresenter);
        englishTopic.init();
    }

    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        englishTopic = new EnglishTopic();
    }
}
