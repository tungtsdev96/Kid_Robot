package com.android.tupple.robot.view.listaudio;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListView;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListViewWrapper;

public class AudioListViewWrapperImpl implements AudioListViewWrapper {

    @Override
    public CleanObservable<AudioListView> getViewCreatedObservable() {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void invalidate() {

    }
}
