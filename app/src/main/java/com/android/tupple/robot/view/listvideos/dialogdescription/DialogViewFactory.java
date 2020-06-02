package com.android.tupple.robot.view.listvideos.dialogdescription;

import android.app.Activity;
import android.app.Dialog;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionView;

public class DialogViewFactory {
    public static DialogDescriptionView<Media> newDialogDescriptionView(Dialog view , Activity activity){
        return new DialogViewImpl(view , activity);
    }
}
