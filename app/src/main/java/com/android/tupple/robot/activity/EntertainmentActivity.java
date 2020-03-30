package com.android.tupple.robot.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.activity.ActivityLauncher;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.AudioListModelFactory;
import com.android.tupple.robot.data.model.mediaobject.EntertainmentModelFactory;
import com.android.tupple.robot.data.model.mediaobject.VideoListModelFactory;
import com.android.tupple.robot.domain.entity.englishtopic.EnglishTopic;
import com.android.tupple.robot.domain.entity.entertainment.Entertainment;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListPresenterImpl;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListViewWrapper;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentPresenterImpl;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.audiolist.AudioListFragment;
import com.android.tupple.robot.view.audiolist.AudioListViewWrapperFactory;
import com.android.tupple.robot.view.entertainment.EntertainmentViewFactory;
import com.android.tupple.robot.view.videolist.VideoListFragment;
import com.android.tupple.robot.view.videolist.VideoListViewWrapperFactory;

public class EntertainmentActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private Entertainment entertainment;
    @Override
    protected int getLayoutContent() {
        return R.layout.fragment_entertainment;
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
        EntertainmentPresenterImpl<Fragment> entertainmentPresenter = new EntertainmentPresenterImpl();
        EntertainmentView<Fragment> entertainmentView = EntertainmentViewFactory.newEntertainmentView(this,bundle);
        EntertainmentModel<Fragment> entertainmentModel = EntertainmentModelFactory.newEntertainmentModel(this);

        entertainmentPresenter.setEntertainmentView(entertainmentView);
        entertainmentPresenter.setEntertainmentModel(entertainmentModel);
        ////////////////////////////////////////////
        VideoListPresenterImpl<Media> videoListPresenter = new VideoListPresenterImpl<>();
        VideoListViewWrapper<Media> videoListViewWrapper = VideoListViewWrapperFactory.newVideoListViewWrapper(getSupportFragmentManager(), bundle);
        VideoListModel<Media> videoListModel = VideoListModelFactory.newVideoListModel(this);
        videoListPresenter.setVideoListViewWrapper(videoListViewWrapper);
        videoListPresenter.setVideoListModel(videoListModel);
        //videoListPresenter.setOnItemVideoClickObserver(mActivityLauncher::launchVideoPlayerActivity);
        ///////////////////////////////////////////
        AudioListPresenterImpl<Media> audioListPresenter = new AudioListPresenterImpl<>();
        AudioListViewWrapper<Media> audioListViewWrapper = AudioListViewWrapperFactory.newAudioListViewWrapper(getSupportFragmentManager(), bundle);
        AudioListModel<Media> audioListModel = AudioListModelFactory.newAudioListModel(this);
        audioListPresenter.setAudioListViewWrapper(audioListViewWrapper);
        audioListPresenter.setAudioListModel(audioListModel);
        ///////////////////////////////////////////
        entertainmentPresenter.setVideoListPresenter(videoListPresenter);
        entertainmentPresenter.setAudioListPresenter(audioListPresenter);
        entertainment.setEntertainmentPresenter(entertainmentPresenter);
        entertainment.init();
    }
//    public static final String TAG = "EntertainmentFragment";
//    private static FragmentManager mFragmentManager;
//    private Context mContext;
//
//
//    private RadioButton mBtnAudio, mBtnVideo;
//    private CleanObserver<Fragment> mButtonVideoClickedObserver;
//    private CleanObserver<Fragment> mButtonAudioClickedObserver;
//
//
//
//
//
//    private void initView(View rootView) {
//        mBtnAudio = rootView.findViewById(R.id.btn_tab_audio);
//        mBtnVideo = rootView.findViewById(R.id.btn_tab_video);
//        mBtnVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(mContext, "click video", Toast.LENGTH_SHORT).show();
//                mButtonVideoClickedObserver.onNext(new VideoListFragment());
//            }
//        });
//        mBtnAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(mContext, "click audio", Toast.LENGTH_SHORT).show();
//                mButtonAudioClickedObserver.onNext(new AudioListFragment());
//            }
//        });
//    }
//
//
//    @Override
//    public void initLayout() {
//
//    }
//
//    @Override
//    public CleanObservable<Fragment> getButtonVideoClickedObservable() {
//        return CleanObservable.create(cleanObserver -> mButtonVideoClickedObserver = cleanObserver);
//    }
//
//    @Override
//    public CleanObservable<Fragment> getButtonAudioClickedObservable() {
//        return CleanObservable.create(cleanObserver -> mButtonAudioClickedObserver = cleanObserver);
//    }
//
//    @Override
//    protected int getLayoutContent() {
//        return R.layout.fragment_entertainment;
//    }
//
//    @Override
//    protected void onCreatedActivity(Bundle savedInstanceState) {
//        if (mViewCreatedObserver != null) {
//            mViewCreatedObserver.onNext(this);
//        }
//    }
}

