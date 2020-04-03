package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.VideoListModelFactory;
import com.android.tupple.robot.domain.entity.medialist.MediaList;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.view.listvideos.ListVideosViewFactory;

public class ListFilmsActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private MediaList mediaList;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_list_films;
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
    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        mediaList = new MediaList();
    }
    private void inject(Bundle bundle) {
        VideoListPresenterImpl<Media> videoListPresenter = new VideoListPresenterImpl<>();
        VideoListModel<Media> videoListModel = VideoListModelFactory.newVideoListModel(this);
        VideoListView<Media> videoListView = ListVideosViewFactory.newVideoListView(this,bundle);
        videoListPresenter.setmVideoListView(videoListView);
        videoListPresenter.setVideoListModel(videoListModel);
        videoListPresenter.setOnCloseButtonHandler(this::onBackPressed);
        mediaList.setMediaListPresenter(videoListPresenter);
        mediaList.init();
    }
}
