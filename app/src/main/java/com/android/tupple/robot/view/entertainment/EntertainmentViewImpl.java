package com.android.tupple.robot.view.entertainment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.view.audiolist.AudioListFragment;
import com.android.tupple.robot.view.videolist.VideoListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EntertainmentViewImpl implements EntertainmentView {
    public static final String TAG = "EntertainmentFragment";
    private Activity mActivity;
    private Bundle bundle;
    private FloatingActionButton mBtnClose;
    private CleanObserver<EntertainmentView<Fragment>> mViewCreatedObserver;

    private RadioButton mBtnAudio, mBtnVideo;
    private CleanObserver<Fragment> mButtonVideoClickedObserver;
    private CleanObserver<Fragment> mButtonAudioClickedObserver;
    private CleanObserver mButtonCloseClickedObserver;

    public EntertainmentViewImpl(Activity mActivity, Bundle bundle) {
        this.mActivity = mActivity;
        this.bundle = bundle;
    }

    @Override
    public void initLayout() {
        mBtnAudio = mActivity.findViewById(R.id.btn_tab_audio);
        mBtnVideo = mActivity.findViewById(R.id.btn_tab_video);
        mBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "click video", Toast.LENGTH_SHORT).show();
                if (mButtonAudioClickedObserver != null)
                    mButtonVideoClickedObserver.onNext(new VideoListFragment());
            }
        });
        mBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "click audio", Toast.LENGTH_SHORT).show();
                if (mButtonAudioClickedObserver != null)
                    mButtonAudioClickedObserver.onNext(new AudioListFragment());
            }
        });
        mBtnClose = mActivity.findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonCloseClickedObserver != null)
                    mButtonCloseClickedObserver.onNext();
            }
        });
    }

    @Override
    public void closeEntertainmentActivity() {
        mActivity.finish();
    }

    @Override
    public CleanObservable<Fragment> getButtonVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonVideoClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<Fragment> getButtonAudioClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonAudioClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getButtonCloseClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonCloseClickedObserver = cleanObserver);
    }
//    private Bundle mBundle;
//
//    private static FragmentManager mFragmentManager;
//    private EntertainmentActivity mEntertainmentActivity;
//    private CleanObserver<EntertainmentView<Fragment>> mViewCreatedObserver;
//    EntertainmentViewImpl(FragmentManager fragmentManager, Bundle bundle) {
//        mFragmentManager = fragmentManager;
//        this.mBundle = bundle;
//    }
//    @Override
//    public CleanObservable<EntertainmentView<Fragment>> getViewCreatedObservable() {
//        return CleanObservable.create(cleanObserver -> mViewCreatedObserver = cleanObserver);
//    }
//
//    @Override
//    public void show() {
//        //createEntertainmentFragment();
//        setViewCreatedObserverOnFragment();
//    }
////    private void createEntertainmentFragment() {
////        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
////        mEntertainmentActivity = (EntertainmentActivity) mFragmentManager.findFragmentByTag(EntertainmentActivity.TAG);
////        if (mEntertainmentActivity == null) {
////            mEntertainmentActivity = EntertainmentActivity.newInstance();
////        }
////        fragmentTransaction.replace(R.id.content_menu, mEntertainmentActivity, EntertainmentActivity.TAG);
////        fragmentTransaction.commitAllowingStateLoss();
////    }
//
//    private void setViewCreatedObserverOnFragment() {
//        if (mViewCreatedObserver != null) {
//            mEntertainmentActivity.setViewCreatedObserver(mViewCreatedObserver);
//        }
//    }
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void invalidate() {
//
//    }

}
