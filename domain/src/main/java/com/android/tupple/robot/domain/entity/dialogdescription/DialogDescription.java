package com.android.tupple.robot.domain.entity.dialogdescription;

public class DialogDescription {
    private DialogDescriptionPresenter mDialogDescriptionPresenter;



    public DialogDescriptionPresenter getDialogDescriptionPresenter() {
        return mDialogDescriptionPresenter;
    }

    public void setDialogDescriptionPresenter(DialogDescriptionPresenter mDialogDescriptionPresenter) {
        this.mDialogDescriptionPresenter = mDialogDescriptionPresenter;
    }

    public void init() {
        mDialogDescriptionPresenter.init();
    }

    public void start() {
        mDialogDescriptionPresenter.start();
    }

    public void finish() {
        mDialogDescriptionPresenter.finish();
    }
}
