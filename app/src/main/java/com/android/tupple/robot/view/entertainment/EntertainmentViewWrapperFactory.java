package com.android.tupple.robot.view.entertainment;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;
import com.android.tupple.robot.view.englishtopic.EnglishTopicViewWrapperImpl;

public class EntertainmentViewWrapperFactory {
    public static EntertainmentViewWrapper newEntertainmentViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new EntertainmentViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
