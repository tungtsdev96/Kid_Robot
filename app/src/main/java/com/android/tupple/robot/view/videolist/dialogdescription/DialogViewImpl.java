package com.android.tupple.robot.view.videolist.dialogdescription;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionView;
import com.android.tupple.robot.utils.downloadutils.DownloadUtils;
import com.bumptech.glide.Glide;

import java.io.File;

public class DialogViewImpl implements DialogDescriptionView<Media> {
    private ImageView imgThumbnail;
    private Button btnDownload, btnExit, btnPlay;
    private TextView txtTitle, txtDescription;

    private CleanObserver mDownloadButtonClickedObserver;
    private CleanObserver mExitButtonClickedObserver;
    private CleanObserver mPlayButtonClickedObserver;

    private Dialog dialog;

    private Activity mActivity;

    public DialogViewImpl(Dialog dialog, Activity activity) {
        this.dialog = dialog;
        this.mActivity = activity;
    }

    @Override
    public void initLayout() {
        imgThumbnail = dialog.findViewById(R.id.thumbnail_video_dialog);
        btnDownload = dialog.findViewById(R.id.btn_download_dialog);
        btnPlay = dialog.findViewById(R.id.btn_play_dialog);
        btnExit = dialog.findViewById(R.id.btn_exit_dialog);
        txtTitle = dialog.findViewById(R.id.txt_title_dialog);
        txtDescription = dialog.findViewById(R.id.txt_description_dialog);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadButtonClickedObserver != null)
                    mDownloadButtonClickedObserver.onNext();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mExitButtonClickedObserver != null)
                    mExitButtonClickedObserver.onNext();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayButtonClickedObserver != null)
                    mPlayButtonClickedObserver.onNext();
            }
        });
    }

    @Override
    public void setInfo(Media media) {
        txtDescription.setText(media.getDescription());
        txtTitle.setText(media.getTitle());
        File pictureFile = new File(mActivity.getCacheDir(), media.getTitle() + ".png");
        if (pictureFile.exists()) {
            Glide.with(mActivity).load(pictureFile.getAbsolutePath()).into(imgThumbnail);
        }
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mExitButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<Media> getPlayButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mPlayButtonClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getDownloadButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mDownloadButtonClickedObserver = cleanObserver);
    }

    @Override
    public void showButtonDownloadOrPlay(Media media) {
        if (media.isLocal()) {
            btnDownload.setVisibility(View.GONE);
            btnPlay.setVisibility(View.VISIBLE);
        } else {
            btnDownload.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
        }
    }


    @Override
    public void exitDialog() {
        dialog.dismiss();
    }



}
