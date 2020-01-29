package com.android.tupple.robot.domain.presenter.data;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tung.ts on 1/29/2020.
 */

public interface VocabModel<Vocabulary> {

    CleanObservable<Vocabulary> getVocabularybyId(int vocabId);

    CleanObservable<Vocabulary> getListVocabularyByLessonId(int lessonId);

    CleanObservable<Vocabulary> getListVocabularyByTopicId(int topicId);

}