package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level1ViewWrapper<LessonData, Topic, Vocabulary> extends IView {

    CleanObservable<Level1View<LessonData, Topic, Vocabulary>> getViewCreatedObservable();

}
