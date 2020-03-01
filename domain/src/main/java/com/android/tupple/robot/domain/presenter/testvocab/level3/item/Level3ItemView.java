package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.testvocab.level3.RecordState;
import com.android.tupple.robot.domain.presenter.testvocab.level3.ResultState;

/**
 * Created by tungts on 2020-02-22.
 */

public interface Level3ItemView<Vocabulary> {

    void setVocabulary(Vocabulary vocabulary);

    void startRecording();

    void setTextYourAnswer(String text);

    void setError();

    void setTextResult(ResultState state);

    void setStateRecording(RecordState state);

    CleanObservable getBtnPronounceClickedObservable();

    CleanObservable<Boolean> getBtnRecordingClickedObservable();

    CleanObservable<String> getRecordStateDoneObservable();

}
