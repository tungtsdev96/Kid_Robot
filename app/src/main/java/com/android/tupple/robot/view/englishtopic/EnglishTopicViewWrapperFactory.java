package com.android.tupple.robot.view.englishtopic;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.common.data.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicViewWrapperFactory {

    public static EnglishTopicViewWrapper<Topic> newEnglishTopicViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new EnglishTopicViewWrapperImpl(fragmentManager, bundle);
    }

}
