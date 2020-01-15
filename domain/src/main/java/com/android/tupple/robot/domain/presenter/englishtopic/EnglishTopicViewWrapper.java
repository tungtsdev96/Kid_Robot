package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IView;

/**
 * Created by tungts on 2020-01-15.
 */

public interface EnglishTopicViewWrapper<Topic> extends IView {

    CleanObservable<EnglishTopicView<Topic>> getViewCreatedObservable();

}
