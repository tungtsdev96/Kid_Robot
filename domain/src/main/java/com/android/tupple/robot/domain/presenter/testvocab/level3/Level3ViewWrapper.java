package com.android.tupple.robot.domain.presenter.testvocab.level3;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-02-20.
 */

public interface Level3ViewWrapper<LessonData, Topic, Vocabulary> extends IView {

    CleanObservable<Level3View<LessonData, Topic, Vocabulary>> getViewCreatedObservable();

}

