package com.android.tupple.robot.domain.presenter.data;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public interface LearningVocabModel<Vocabulary> extends IModel {

    CleanObservable<List<Vocabulary>> makeListVocabLearningByTopicId(int topicId);

    CleanObservable<List<Vocabulary>> getListVocabLearningByLessonId(int lessonId);

    CleanObservable<Boolean> updateLearnLessonDone();

    List<Vocabulary> getListVocabLearning();
}
