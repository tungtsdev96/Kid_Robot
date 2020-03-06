package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level1View<LessonData, Topic, Vocabulary> {

    void playAudioVocab(Vocabulary question);

    void showQuestion(TestVocabLevel testVocabLevel, Vocabulary vocabulary, List<Vocabulary> listAnswers);

    void notifyMustSelectedAnswer();

    void setEnableBtnCheckAnswer(boolean isSelected);

    void setAnswerSelected(int answerSelected);

    void showLayoutAnswerResult(boolean isResult, int resultPosition);

    void hideLayoutAnswerResult();

    CleanObservable<Integer> getAnswerSelectedObservable();

    CleanObservable getBtnCheckAnswerClickedObservable();

    CleanObservable getBtnPronounceVocabClickedObservable();

}
