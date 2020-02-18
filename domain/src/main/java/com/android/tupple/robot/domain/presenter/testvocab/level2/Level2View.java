package com.android.tupple.robot.domain.presenter.testvocab.level2;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level2View<LessonData, Topic, Vocabulary> {

    void showQuestion(char[] question);

    void showAnswer(char[] answer);

}