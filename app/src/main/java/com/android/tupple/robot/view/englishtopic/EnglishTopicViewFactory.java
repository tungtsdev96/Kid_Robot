package com.android.tupple.robot.view.englishtopic;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicViewFactory {

    public static EnglishTopicView<Topic> newEnglishTopicView(Activity activity, Bundle bundle) {
        return new EnglishTopicViewImpl(activity, bundle);
    }

}
