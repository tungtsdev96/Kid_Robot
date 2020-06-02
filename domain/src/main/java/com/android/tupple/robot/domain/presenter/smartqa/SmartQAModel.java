package com.android.tupple.robot.domain.presenter.smartqa;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tungts on 3/22/20.
 */

public interface SmartQAModel<QAResponse> {

    CleanObservable<QAResponse> getAnswerObservable(String question);

    CleanObservable<QAResponse> getAnswerObservable(String pathFile, String xx);

    CleanObservable<QAResponse> getAnswerObservable(short[] data);

    void stop();

    void destroy();

}
