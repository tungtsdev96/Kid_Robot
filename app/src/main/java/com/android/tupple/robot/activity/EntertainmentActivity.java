package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.AudioListModelFactory;
import com.android.tupple.robot.data.model.mediaobject.EntertainmentModelFactory;
import com.android.tupple.robot.data.model.mediaobject.VideoListModelFactory;
import com.android.tupple.robot.data.model.mediaobject.VideoYoutubeListModelFactory;
import com.android.tupple.robot.domain.entity.entertainment.Entertainment;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentPresenterImpl;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListModel;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListViewWrapper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EntertainmentActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private Entertainment entertainment;
    private CardView mBtnAudio, mBtnVideo, mBtnVideoYoutube;
    private FloatingActionButton mBtnClose;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_entertainment;
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
        entertainment = new Entertainment();
    }

    private void inject(Bundle bundle) {
//        EntertainmentPresenterImpl entertainmentPresenter = new EntertainmentPresenterImpl();
//        EntertainmentView entertainmentView = EntertainmentViewFactory.newEntertainmentView(this,bundle);
//        EntertainmentModel entertainmentModel = EntertainmentModelFactory.newEntertainmentModel(this);
//
//        entertainmentPresenter.setEntertainmentView(entertainmentView);
//        entertainmentPresenter.setEntertainmentModel(entertainmentModel);
//        entertainmentPresenter.setCloseButtonHandler(this::finish);
//
//        entertainmentPresenter.setmButtonAudioClickedObserver(activityLauncher::launchListAudioActivity);
//        entertainmentPresenter.setmButtonVideoClickedObserver(activityLauncher::launchListFilmsActivity);
//        entertainmentPresenter.setmButtonVideoYoutubeClickedObserver(activityLauncher::launchListYoutubeVideoActivity);
//        entertainment.setEntertainmentPresenter(entertainmentPresenter);
//        entertainment.init();
        mBtnAudio = findViewById(R.id.btn_tab_audio);
        mBtnVideo = findViewById(R.id.btn_tab_video);
        mBtnVideoYoutube = findViewById(R.id.btn_tab_video_youtube);
        mBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLauncher.launchListFilmsActivity();
            }
        });
        mBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLauncher.launchListAudioActivity();
            }
        });
        mBtnVideoYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityLauncher.launchListYoutubeVideoActivity();
            }
        });
        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

