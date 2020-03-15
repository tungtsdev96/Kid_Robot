package com.android.tupple.robot.activity;

import android.Manifest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.MenuItemData;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.model.alarm.AlarmModelFactory;
import com.android.tupple.robot.data.model.mediaobject.AudioListModelFactory;
import com.android.tupple.robot.data.model.mediaobject.EntertainmentModelFactory;
import com.android.tupple.robot.data.model.mediaobject.VideoListModelFactory;
import com.android.tupple.robot.domain.entity.menumain.MenuMain;
import com.android.tupple.robot.domain.entity.menumain.MenuType;
import com.android.tupple.robot.domain.presenter.alarm.AlarmModel;
import com.android.tupple.robot.domain.presenter.alarm.AlarmPresenterImpl;
import com.android.tupple.robot.domain.presenter.alarm.AlarmViewWrapper;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListPresenterImpl;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListViewWrapper;
import com.android.tupple.robot.domain.presenter.drawer.DrawerModel;
import com.android.tupple.robot.domain.presenter.drawer.DrawerPresenterImpl;
import com.android.tupple.robot.domain.presenter.drawer.DrawerView;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookViewWrapper;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicPresenterImpl;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicViewWrapper;
import com.android.tupple.robot.data.model.drawer.DrawerModelFactory;
import com.android.tupple.robot.data.model.english.EnglishModelFactory;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentPresenterImpl;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentViewWrapper;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.alarm.AlarmViewWrapperFactory;
import com.android.tupple.robot.view.audiolist.AudioListViewWrapperFactory;
import com.android.tupple.robot.view.drawer.DrawerViewFactory;
import com.android.tupple.robot.view.englishbook.EnglishBookViewWrapperFactory;
import com.android.tupple.robot.view.englishtopic.EnglishTopicViewWrapperFactory;
import com.android.tupple.robot.view.entertainment.EntertainmentViewWrapperFactory;
import com.android.tupple.robot.view.videolist.VideoListViewWrapperFactory;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends BaseActivity {

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
        inject(savedInstanceState);

        mMenuMain.init();
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

    private void inject(Bundle bundle) {
        injectDrawer(bundle);
        injectEnglishBook(bundle);
        injectEnglishTopic(bundle);
        injectAlarmClock(bundle);
        injectLearningSchedule(bundle);
        injectEntertainment(bundle);
    }

    private void injectDrawer(Bundle bundle) {
        DrawerPresenterImpl<MenuItemData> drawerPresenter = new DrawerPresenterImpl<>();
        DrawerView<MenuItemData> drawerView = DrawerViewFactory.newDrawerView(this);
        DrawerModel<MenuItemData> drawerModel = DrawerModelFactory.newDrawerModel(this);

        drawerPresenter.setDrawerView(drawerView);
        drawerPresenter.setDrawerModel(drawerModel);
        mMenuMain.setDrawerViewPresenter(drawerPresenter);
    }

    private void injectEnglishBook(Bundle bundle) {
        EnglishBookPresenterImpl<SchoolBook> englishBookPresenter = new EnglishBookPresenterImpl<>();
        EnglishBookViewWrapper<SchoolBook> englishBookViewWrapper = EnglishBookViewWrapperFactory.newSchoolBookEnglishBookViewWrapper(getSupportFragmentManager(), bundle);
        EnglishBookModel<SchoolBook> englishBookModel = EnglishModelFactory.newEnglishBookModel(this);

        englishBookPresenter.setEnglishTopicViewWrapper(englishBookViewWrapper);
        englishBookPresenter.setEnglishBookModel(englishBookModel);

        // innit Observerable
        englishBookPresenter.setOnItemBookClickedObserver(mActivityLauncher::launchLessonActivity);

        mMenuMain.setEnglishBookPresenter(englishBookPresenter);
        mMenuMain.setCurrentPresenterByMenuType(MenuType.ENGLISH_BOOK);
    }

    private void injectEnglishTopic(Bundle bundle) {
        EnglishTopicPresenterImpl<Topic> englishTopicPresenter = new EnglishTopicPresenterImpl<>();
        EnglishTopicViewWrapper<Topic> englishTopicViewWrapper = EnglishTopicViewWrapperFactory.newEnglishTopicViewWrapper(getSupportFragmentManager(), bundle);
        EnglishTopicModel<Topic>  englishTopicModel = EnglishModelFactory.newEnglishTopicModel(this);

        englishTopicPresenter.setEnglishTopicViewWrapper(englishTopicViewWrapper);
        englishTopicPresenter.setEnglishBookModel(englishTopicModel);

        // innit Observerable
        englishTopicPresenter.setOnItemBookClickedObserver(mActivityLauncher::launchLearningVocabActivity);

        mMenuMain.setEnglishTopicPresenter(englishTopicPresenter);
    }

    private void injectAlarmClock(Bundle bundle) {
        AlarmPresenterImpl alarmPresenter = new AlarmPresenterImpl();
        AlarmViewWrapper alarmViewWrapper = AlarmViewWrapperFactory.newAlarmViewWrapper(getSupportFragmentManager());
        AlarmModel alarmModel = AlarmModelFactory.newAlarmModel(this);

        alarmPresenter.setAlarmViewWrapper(alarmViewWrapper);
        alarmPresenter.setAlarmModel(alarmModel);
        mMenuMain.setAlarmPresenter(alarmPresenter);
    }

    private void injectLearningSchedule(Bundle bundle) {

    }
    private void injectEntertainment(Bundle bundle) {
        EntertainmentPresenterImpl<Fragment> entertainmentPresenter = new EntertainmentPresenterImpl();
        EntertainmentViewWrapper<Fragment> entertainmentViewWrapper = EntertainmentViewWrapperFactory.newEntertainmentViewWrapper(getSupportFragmentManager(), bundle);
        EntertainmentModel<Fragment> entertainmentModel = EntertainmentModelFactory.newEntertainmentModel(this);

        entertainmentPresenter.setEntertainmentViewWrapper(entertainmentViewWrapper);
        entertainmentPresenter.setEntertainmentModel(entertainmentModel);
        ////////////////////////////////////////////
        VideoListPresenterImpl<Media> videoListPresenter = new VideoListPresenterImpl<>();
        VideoListViewWrapper<Media> videoListViewWrapper = VideoListViewWrapperFactory.newVideoListViewWrapper(getSupportFragmentManager(), bundle);
        VideoListModel<Media> videoListModel = VideoListModelFactory.newVideoListModel(this);
        videoListPresenter.setVideoListViewWrapper(videoListViewWrapper);
        videoListPresenter.setVideoListModel(videoListModel);
        videoListPresenter.setOnItemVideoClickObserver(mActivityLauncher::launchVideoPlayerActivity);
        ///////////////////////////////////////////
        AudioListPresenterImpl<Media> audioListPresenter = new AudioListPresenterImpl<>();
        AudioListViewWrapper<Media> audioListViewWrapper = AudioListViewWrapperFactory.newAudioListViewWrapper(getSupportFragmentManager(), bundle);
        AudioListModel<Media> audioListModel = AudioListModelFactory.newAudioListModel(this);
        audioListPresenter.setAudioListViewWrapper(audioListViewWrapper);
        audioListPresenter.setAudioListModel(audioListModel);
        audioListPresenter.setOnItemAudioClickObserver(mActivityLauncher::launchAudioPlayerActivity);
        ///////////////////////////////////////////
        entertainmentPresenter.setVideoListPresenter(videoListPresenter);
        entertainmentPresenter.setAudioListPresenter(audioListPresenter);
        mMenuMain.setEntertainmentPresenter(entertainmentPresenter);
    }

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