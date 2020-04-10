package com.android.tupple.robot.view.listvideos.dialogdescription;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.android.tupple.robot.R;
import com.android.tupple.robot.activity.ActivityLauncher;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.entity.dialogdescription.DialogDescription;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionView;
import com.android.tupple.robot.utils.downloadutils.DownloadMediaUtils;

public class MyDialogDescription extends Dialog {
    private Activity mActivity;
    private Media media;
    private ActivityLauncher mActivityLauncher;
    private showProgress showProgress;
    public MyDialogDescription(@NonNull Context context) {
        super(context);
    }


    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public void setMedia(Media media) {
        this.media = media;
    }
//     public void setVideoListFragment(VideoListFragment videoListFragment){
//        this.videoListFragment = videoListFragment;
//     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_video_description);
        mActivityLauncher = new ActivityLauncher(mActivity);
        inject(mActivity , media);
       // showProgress = videoListFragment;
    }

    private void inject(Activity activity, Media media) {
        DialogDescriptionPresenterImpl<Media> dialogDescriptionPresenter = new DialogDescriptionPresenterImpl();
        DialogDescriptionView<Media> dialogDescriptionView = DialogViewFactory.newDialogDescriptionView(this, activity);
        dialogDescriptionPresenter.setCurrentVideo(media);
        dialogDescriptionPresenter.setDialogDescriptionView(dialogDescriptionView);
        dialogDescriptionPresenter.setOnPlayButtonClickedObserver(mActivityLauncher::launchVideoPlayerActivity);
        dialogDescriptionPresenter.setOnDownloadButtonClickedObserver(this::downloadVideo);
        DialogDescription dialogDescription = new DialogDescription();
        dialogDescription.setDialogDescriptionPresenter(dialogDescriptionPresenter);
        dialogDescription.init();
    }

    private void downloadVideo(Media media) {
//        DownloadUtils downloadUtils = new DownloadUtils(videoListFragment,media.getMedia_url(),media.getTitle());
//        downloadUtils.download();
//        showProgress.showProgressbar(media);
        DownloadMediaUtils.getInstance(mActivity, media).download();
    }

//    private void launchVideoPlayerActivity(Media media) {
//        Intent intent = new Intent(mActivity, VideoPlayerActivity.class);
//        intent.putExtra(EntertainmentConstant.VIDEO_INTENT, media.getMedia_url());
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        ActivityUtils.startActivty(mActivity, intent);
//    }
}
