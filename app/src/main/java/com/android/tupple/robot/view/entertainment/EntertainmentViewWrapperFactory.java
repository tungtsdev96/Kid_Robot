package com.android.tupple.robot.view.entertainment;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;

public class EntertainmentViewWrapperFactory {
    public static EntertainmentViewWrapper newEntertainmentViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new EntertainmentViewWrapperImpl(fragmentManager, bundle);
        //return null;
    }
}
