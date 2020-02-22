package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface EnglishTopicModel<Topic> extends IModel {

    CleanObservable<List<Topic>> getAllTopic();

}
