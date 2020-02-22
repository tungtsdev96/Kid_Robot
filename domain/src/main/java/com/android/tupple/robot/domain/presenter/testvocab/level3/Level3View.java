package com.android.tupple.robot.domain.presenter.testvocab.level3;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-02-20.
 */

public interface Level3View<LessonData, Topic, Vocabulary> {

    void setListLearningVocab(List<Vocabulary> vocabularies);

    CleanObservable getBtnPreviousClickedObservable();

    CleanObservable getBtnNextClickedObservable();

    CleanObservable getBtnRecordingClickedObservable();
}
