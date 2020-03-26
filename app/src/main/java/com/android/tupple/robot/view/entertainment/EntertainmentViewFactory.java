package com.android.tupple.robot.view.entertainment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;

public class EntertainmentViewFactory {
    public static EntertainmentView newEntertainmentView(Activity activity, Bundle bundle) {
        return new EntertainmentViewImpl(activity, bundle);
        //return null;
    }
}
