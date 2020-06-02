package com.android.tupple.robot.domain.presenter.englishtopic;

import com.android.tupple.cleanobject.CleanObservable;

import java.util.List;

/**
 * Created by tungts on 2020-01-12.
 */

public interface EnglishTopicView<Topic> {

    void initLayout();

    void setListTopic(List<Topic> listTopic);


    CleanObservable<Topic> getItemTopicClickedObservable();

    CleanObservable getButtonCloseClickedObservable();
}
