package com.android.tupple.robot.domain.entity.testvocab;

/**
 * Created by tungts on 2020-03-09.
 */

public interface TestProgressPresenter {

    void start();

    void addRightAnswer(boolean isRight, double progress);

}
