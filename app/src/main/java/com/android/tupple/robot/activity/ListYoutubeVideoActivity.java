package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.VideoYoutubeListModelFactory;
import com.android.tupple.robot.domain.entity.medialist.MediaList;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListModel;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListView;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListViewWrapper;
import com.android.tupple.robot.view.listvideoyoutube.ListVideoYoutubeViewFactory;
import com.android.tupple.robot.view.listvideoyoutube.VideoYoutubeListViewWrapperFactory;

public class ListYoutubeVideoActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private MediaList mediaList;


    @Override
    protected int getLayoutContent() {
        return R.layout.activity_list_youtube_video;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        injectFirstBatch();
        inject(bundle);
    }

    private void inject(Bundle bundle) {
        VideoYoutubeListPresenterImpl<Media> videoYoutubeListPresenter = new VideoYoutubeListPresenterImpl<>();
        VideoYoutubeListView<Media> videoYoutubeListView = ListVideoYoutubeViewFactory.newVideoYoutubeListView(this, bundle);
        VideoYoutubeListModel<Media> videoYoutubeListModel = VideoYoutubeListModelFactory.newVideoListModel(this);
        videoYoutubeListPresenter.setVideoYoutubeListModel(videoYoutubeListModel);
        videoYoutubeListPresenter.setmVideoListView(videoYoutubeListView);
        videoYoutubeListPresenter.setOnItemVideoClickObserver(activityLauncher::launchVideoYoutubePlayerActivity);
        mediaList.setMediaListPresenter(videoYoutubeListPresenter);
        mediaList.init();
    }

    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        mediaList = new MediaList();
    }
}
