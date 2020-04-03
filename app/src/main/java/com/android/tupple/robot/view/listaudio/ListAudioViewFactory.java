package com.android.tupple.robot.view.listaudio;

import android.app.Activity;
import android.os.Bundle;

import com.android.tupple.robot.domain.presenter.listaudio.AudioListView;

public class ListAudioViewFactory {
    public static AudioListView newAudioListView(Activity activity, Bundle bundle) {
        return new ListAudioViewImpl(activity, bundle);
        //return null;
    }
}
