package com.android.tupple.robot.view.listvideoyoutube;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListView;

import java.util.List;

public class ListVideoYoutubeViewImpl implements VideoYoutubeListView<Media> {
    public static final String TAG = "ListVideoYoutubeViewImpl";
    private Activity mActivity;
    private Bundle bundle;
    private CleanObserver mButtonCloseClickedObserver;
    public ListVideoYoutubeViewImpl(Activity mActivity, Bundle bundle) {
        this.mActivity = mActivity;
        this.bundle = bundle;
    }

    private SnapRecycleView mRecyclerViewVideoYoutube;
    private CleanObserver<Media> mItemVideoYoutubeClickedObserver;
    private RecyclerViewVideoYoutubeApdater recyclerViewVideoYoutubeAdapter;

    @Override
    public void initLayout() {
        mRecyclerViewVideoYoutube = mActivity.findViewById(R.id.recycler_video);
        recyclerViewVideoYoutubeAdapter = new RecyclerViewVideoYoutubeApdater(mActivity);
        mRecyclerViewVideoYoutube.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false));
        mRecyclerViewVideoYoutube.setAdapter(recyclerViewVideoYoutubeAdapter);
        recyclerViewVideoYoutubeAdapter.setOnItemVideoClickedListener(this::handleItemVideoClicked);

        mActivity.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonCloseClickedObserver != null)
                    mButtonCloseClickedObserver.onNext();
            }
        });
    }

    private void handleItemVideoClicked(int position) {
        if (mItemVideoYoutubeClickedObserver != null) {
            mItemVideoYoutubeClickedObserver.onNext(recyclerViewVideoYoutubeAdapter.getVideoByPosition(position));
        }
    }

    @Override
    public void setListVideoYoutube(List<Media> listMedia) {
        recyclerViewVideoYoutubeAdapter.setListData(listMedia);
    }

    @Override
    public CleanObservable<Media> getItemVideoYoutubeClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemVideoYoutubeClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonCloseClickedObserver = cleanObserver);
    }
}
