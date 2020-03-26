package com.android.tupple.robot.domain.presenter.videolist.dialog_description;

import android.app.Activity;
import android.view.View;

import com.android.tupple.cleanobject.CleanObservable;

public interface DialogDescriptionView<Media> {
    void initLayout();

    void setInfo(Media media); //set title, set Image, set Description;

    CleanObservable getCloseButtonClickedObservable();

    CleanObservable<Media> getPlayButtonClickedObservable();

    CleanObservable getDownloadButtonClickedObservable();

    void showButtonDownloadOrPlay(Media media);

    void exitDialog();

//
//    void createDialog(Activity activity, View view);
//
//    void showDialog();

}
