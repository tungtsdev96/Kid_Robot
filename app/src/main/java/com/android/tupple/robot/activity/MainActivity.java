package com.android.tupple.robot.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.tupple.robot.KidRobotApplication;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.entity.menumain.MenuMain;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends BaseActivity   {

    private MenuMain mMenuMain;
    private ActivityLauncher mActivityLauncher;
    @Override
    protected int getLayoutContent() {
        return R.layout.activity_main;
    }



    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        checkPermission();
        initFirstBatch(savedInstanceState);
        //inject(savedInstanceState);
        mMenuMain.init();
        findViewById(R.id.topic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityLauncher.launchTopictActivity();
            }
        });
        findViewById(R.id.entertainment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityLauncher.launchEntertainmentActivity();
            }
        });
        findViewById(R.id.school_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityLauncher.launchEnglishBookActivity();
            }
        });
        findViewById(R.id.rlt_smart_qa).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SmartQAActivity.class));
        });

        Calendar calendar = Calendar.getInstance();
        TextView tvDate = findViewById(R.id.tv_current_date);
        StringBuilder s = new StringBuilder();
        s.append(calendar.get(Calendar.DAY_OF_MONTH));
        s.append('-');
        s.append("0" + calendar.get(Calendar.MONTH));
        s.append('-');
        s.append(calendar.get(Calendar.YEAR));
        tvDate.setText(s.toString());
    }

    private void checkPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();
    }

    private void initFirstBatch(Bundle bundle) {
        mMenuMain = new MenuMain();
        mActivityLauncher = new ActivityLauncher(this);
    }

//    private void inject(Bundle bundle) {
//        injectDrawer(bundle);
//        injectEnglishBook(bundle);
//        injectEnglishTopic(bundle);
//        injectAlarmClock(bundle);
//        injectLearningSchedule(bundle);
//        injectEntertainment(bundle);
//    }
//
//    private void injectDrawer(Bundle bundle) {
//        DrawerPresenterImpl<MenuItemData> drawerPresenter = new DrawerPresenterImpl<>();
//        DrawerView<MenuItemData> drawerView = DrawerViewFactory.newDrawerView(this);
//        DrawerModel<MenuItemData> drawerModel = DrawerModelFactory.newDrawerModel(this);
//
//        drawerPresenter.setDrawerView(drawerView);
//        drawerPresenter.setDrawerModel(drawerModel);
//        mMenuMain.setDrawerViewPresenter(drawerPresenter);
//    }
//
//    private void injectEnglishBook(Bundle bundle) {
//        EnglishBookPresenterImpl<SchoolBook> englishBookPresenter = new EnglishBookPresenterImpl<>();
//        EnglishBookViewWrapper<SchoolBook> englishBookViewWrapper = EnglishBookViewWrapperFactory.newSchoolBookEnglishBookViewWrapper(getSupportFragmentManager(), bundle);
//        EnglishBookModel<SchoolBook> englishBookModel = EnglishModelFactory.newEnglishBookModel(this);
//
//        englishBookPresenter.setEnglishTopicViewWrapper(englishBookViewWrapper);
//        englishBookPresenter.setEnglishBookModel(englishBookModel);
//
//        // innit Observerable
//        englishBookPresenter.setOnItemBookClickedObserver(mActivityLauncher::launchLessonActivity);
//
//        mMenuMain.setEnglishBookPresenter(englishBookPresenter);
//        mMenuMain.setCurrentPresenterByMenuType(MenuType.ENGLISH_BOOK);
//    }
//
//    private void injectEnglishTopic(Bundle bundle) {
//        EnglishTopicPresenterImpl<Topic> englishTopicPresenter = new EnglishTopicPresenterImpl<>();
//        EnglishTopicViewWrapper<Topic> englishTopicViewWrapper = EnglishTopicViewWrapperFactory.newEnglishTopicViewWrapper(getSupportFragmentManager(), bundle);
//        EnglishTopicModel<Topic>  englishTopicModel = EnglishModelFactory.newEnglishTopicModel(this);
//
//        englishTopicPresenter.setEnglishTopicViewWrapper(englishTopicViewWrapper);
//        englishTopicPresenter.setEnglishBookModel(englishTopicModel);
//
//        // innit Observerable
//        englishTopicPresenter.setOnItemBookClickedObserver(mActivityLauncher::launchLearningVocabActivity);
//
//        mMenuMain.setEnglishTopicPresenter(englishTopicPresenter);
//    }
//
//    private void injectAlarmClock(Bundle bundle) {
//        AlarmPresenterImpl alarmPresenter = new AlarmPresenterImpl();
//        AlarmViewWrapper alarmViewWrapper = AlarmViewWrapperFactory.newAlarmViewWrapper(getSupportFragmentManager());
//        AlarmModel alarmModel = AlarmModelFactory.newAlarmModel(this);
//
//        alarmPresenter.setAlarmViewWrapper(alarmViewWrapper);
//        alarmPresenter.setAlarmModel(alarmModel);
//        mMenuMain.setAlarmPresenter(alarmPresenter);
//    }
//
//    private void injectLearningSchedule(Bundle bundle) {
//
//    }
//    private void injectEntertainment(Bundle bundle) {
//        EntertainmentPresenterImpl<Fragment> entertainmentPresenter = new EntertainmentPresenterImpl();
//        EntertainmentViewWrapper<Fragment> entertainmentViewWrapper = EntertainmentViewWrapperFactory.newEntertainmentViewWrapper(getSupportFragmentManager(), bundle);
//        EntertainmentModel<Fragment> entertainmentModel = EntertainmentModelFactory.newEntertainmentModel(this);
//
//        entertainmentPresenter.setEntertainmentViewWrapper(entertainmentViewWrapper);
//        entertainmentPresenter.setEntertainmentModel(entertainmentModel);
//        ////////////////////////////////////////////
//        VideoListPresenterImpl<Media> videoListPresenter = new VideoListPresenterImpl<>();
//        VideoListViewWrapper<Media> videoListViewWrapper = VideoListViewWrapperFactory.newVideoListViewWrapper(getSupportFragmentManager(), bundle);
//        VideoListModel<Media> videoListModel = VideoListModelFactory.newVideoListModel(this);
//        videoListPresenter.setVideoListViewWrapper(videoListViewWrapper);
//        videoListPresenter.setVideoListModel(videoListModel);
//        //videoListPresenter.setOnItemVideoClickObserver(mActivityLauncher::launchVideoPlayerActivity);
//        ///////////////////////////////////////////
//        AudioListPresenterImpl<Media> audioListPresenter = new AudioListPresenterImpl<>();
//        AudioListViewWrapper<Media> audioListViewWrapper = AudioListViewWrapperFactory.newAudioListViewWrapper(getSupportFragmentManager(), bundle);
//        AudioListModel<Media> audioListModel = AudioListModelFactory.newAudioListModel(this);
//        audioListPresenter.setAudioListViewWrapper(audioListViewWrapper);
//        audioListPresenter.setAudioListModel(audioListModel);
//        ///////////////////////////////////////////
//        entertainmentPresenter.setVideoListPresenter(videoListPresenter);
//        entertainmentPresenter.setAudioListPresenter(audioListPresenter);
//        mMenuMain.setEntertainmentPresenter(entertainmentPresenter);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuMain.start();
    }

    @Override
    protected void onDestroy() {
        mMenuMain.stop();
        super.onDestroy();
    }

}

