package com.android.tupple.robot.domain.presenter.data;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tung.ts on 1/29/2020.
 */

public interface VocabularyModel<Vocabulary> extends IModel {

    CleanObservable<Vocabulary> getVocabularybyId(int vocabId);

    CleanObservable<List<Vocabulary>> getListVocabularyByLessonId(int lessonId);

    CleanObservable<List<Vocabulary>> getListVocabularyByTopicId(int topicId);

}