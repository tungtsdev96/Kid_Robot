package com.android.tupple.robot.domain.presenter.smartqa;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 3/22/20.
 */

public interface SmartQAView<QAResponse> {

    void initView();

    void showPopUpSpeedToText();

    // 0: way to ask robot, 1: updating get answer from server
    void updateFromRobot(int type);

    void addResultFromRobot(QAResponse response);

    void addQuestionFromUser(String question);

    void showHaveNotAnswer();

    void showError();

    void onStop();

    void onDestroy();

    CleanObservable getCloseButtonClickedObservable();

    CleanObservable getAskRobotButtonClickedObservable();

    CleanObservable<String> getResultSpeedToTextObservable();

    CleanObservable<String> getResultBufferAudioObservable();

}
