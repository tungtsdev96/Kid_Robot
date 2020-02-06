package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level1View<LessonData, Topic, Vocabulary> {

    void setData(List<Vocabulary> items);

    CleanObservable<Boolean> getAnswerChooseObservable();

}
