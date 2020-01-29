package com.android.tupple.robot.model.english;

import android.content.Context;

import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishTopicModelImpl implements EnglishTopicModel<Topic> {

    private Context mContext;

    public EnglishTopicModelImpl(Context mContext) {
        this.mContext = mContext;
    }
}
