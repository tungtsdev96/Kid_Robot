package com.android.tupple.robot.view.listvideos.dialogdescription;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.android.tupple.robot.KidRobotApplication;
import com.android.tupple.robot.R;
import com.android.tupple.robot.activity.ActivityLauncher;
import com.android.tupple.robot.activity.MainActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.entity.dialogdescription.DialogDescription;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.dialog_description.DialogDescriptionView;
import com.android.tupple.robot.utils.downloadutils.DialogUtils;
import com.android.tupple.robot.utils.downloadutils.DownloadMediaUtils;

public class MyDialogDescription extends Dialog {
    private Activity mActivity;
    private Media media;
    private ActivityLauncher mActivityLauncher;

    public MyDialogDescription(@NonNull Context context) {

        super(context);
    }


    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }


    public void setMedia(Media media) {
        this.media = media;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_video_description);
        mActivityLauncher = new ActivityLauncher(mActivity);
        inject(mActivity, media);
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
        if (!isNetworkConnected()) {
            new DialogUtils(mActivity, "Thông báo", "Vui lòng kiểm tra kết nối wifi", R.drawable.ic_success).showDialog();
        } else {
           // Log.d("testtest", media.getTitle() + " " + media.getId());
            KidRobotApplication kidRobotApplication = (KidRobotApplication) mActivity.getApplicationContext();
            //Log.d("testtt" , kidRobotApplication.getCurrentActivity()+" ");
            new DownloadMediaUtils(mActivity.getApplicationContext(), kidRobotApplication.getCurrentActivity(), media).download();
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
