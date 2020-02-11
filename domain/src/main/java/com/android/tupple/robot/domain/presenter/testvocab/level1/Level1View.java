package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level1View<LessonData, Topic, Vocabulary> {

    void showQuestion(TestVocabLevel testVocabLevel, Vocabulary vocabulary, List<Vocabulary> listAnswers);

    void notifyMustSelectedAnswer();

    void setEnableBtnCheckAnswer(boolean isSelected);

    void showLayoutAnswerResult(boolean isAnswer);

    void hideLayoutAnswerResult();

    CleanObservable<Integer> getAnswerSelectedObservable();

    CleanObservable getBtnCheckAnswerClickedObservable();

    CleanObservable getBtnPronounceVocabClickedObservable();
}
