package com.android.tupple.robot.view.listvideos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.view.listvideos.dialogdescription.MyDialogDescription;
import com.android.tupple.robot.view.listvideos.dialogdescription.showProgress;

import java.util.List;

public class ListVideosViewImpl implements showProgress, VideoListView<Media> {
    public static final String TAG = "ListVideosViewImpl";
    private Activity mActivity;
    private Bundle bundle;
    private CleanObserver<VideoListView<Media>> mViewCreatedObserver;
    private SnapRecycleView mRecyclerViewVideo;
    private CleanObserver<Media> mItemVideoClickedObserver;
    private RecyclerViewVideoAdapter recyclerViewVideoAdapter;
    private CleanObserver mButtonCloseClickedObserver;
    public ListVideosViewImpl(Activity mActivity, Bundle bundle) {
        this.mActivity = mActivity;
        this.bundle = bundle;
    }

    @Override
    public void initLayout() {
        mRecyclerViewVideo = mActivity.findViewById(R.id.recycler_video);
        recyclerViewVideoAdapter = new RecyclerViewVideoAdapter(mActivity);
        mRecyclerViewVideo.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.HORIZONTAL, false));
        mRecyclerViewVideo.setAdapter(recyclerViewVideoAdapter);
        recyclerViewVideoAdapter.setOnItemVideoClickedListener(this::handleItemVideoClicked);
        mActivity.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonCloseClickedObserver != null)
                    mButtonCloseClickedObserver.onNext();
            }
        });
    }

    private void handleItemVideoClicked(int position) {
        if (mItemVideoClickedObserver != null) {
            mItemVideoClickedObserver.onNext(recyclerViewVideoAdapter.getVideoByPosition(position));
        }
    }

    @Override
    public void setListVideo(List<Media> listMedia) {
        recyclerViewVideoAdapter.setListData(listMedia);
    }

    @Override
    public void showDialogDescription(Media media) {
        MyDialogDescription myDialogDescription = new MyDialogDescription(mActivity);
        myDialogDescription.setActivity((Activity) mActivity);
        myDialogDescription.setMedia(media);
        myDialogDescription.show();
    }

    @Override
    public CleanObservable<Media> getItemVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemVideoClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getCloseButtonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonCloseClickedObserver = cleanObserver);
    }



    private void saveUrlToDatabase() {
    }



    @Override
    public void showProgressbar(Media media) {
        recyclerViewVideoAdapter.setPositionForItemWhenStartDownload(media);
    }
}
