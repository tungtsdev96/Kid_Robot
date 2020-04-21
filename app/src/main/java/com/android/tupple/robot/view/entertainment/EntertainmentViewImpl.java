package com.android.tupple.robot.view.entertainment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EntertainmentViewImpl implements EntertainmentView {
    public static final String TAG = "EntertainmentFragment";
    private Activity mActivity;
    private Bundle bundle;
    private FloatingActionButton mBtnClose;
    private CleanObserver<EntertainmentView> mViewCreatedObserver;

    private RadioButton mBtnAudio, mBtnVideo, mBtnVideoYoutube;
    private CleanObserver mButtonVideoClickedObserver;
    private CleanObserver mButtonAudioClickedObserver;
    private CleanObserver mButtonVideoYoutubeClickedObserver;
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
                //if (mButtonAudioClickedObserver != null)
                    //mButtonVideoClickedObserver.onNext(new VideoListFragment());
            }
        });
        mBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "click audio", Toast.LENGTH_SHORT).show();
                //if (mButtonAudioClickedObserver != null)
                   // mButtonAudioClickedObserver.onNext(new AudioListFragment());
            }
        });
        mBtnVideoYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "click audio", Toast.LENGTH_SHORT).show();
//                if (mButtonVideoYoutubeClickedObserver != null)
//                    mButtonVideoYoutubeClickedObserver.onNext(new VideoYoutubeListFragment());
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
    public CleanObservable getButtonVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonVideoClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getButtonAudioClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonAudioClickedObserver = cleanObserver);
    }



    @Override
    public CleanObservable getButtonCloseClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonCloseClickedObserver = cleanObserver);
    }


}
