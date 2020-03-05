package com.android.tupple.robot.domain.presenter.testvocab;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public interface TestVocabModel<LessonData, Topic, Vocabulary> extends IModel {

    CleanObservable<List<Vocabulary>> transformListVocabLearning(List<Vocabulary> vocabs);

    CleanObservable<List<Vocabulary>> makeListAnswerFromVocab(List<Vocabulary> vocabLearning, Vocabulary vocabulary);

}
