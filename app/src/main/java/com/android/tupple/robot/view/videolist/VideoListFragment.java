package com.android.tupple.robot.view.videolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.utils.downloadutils.DownloadInterface;
import com.android.tupple.robot.view.videolist.dialogdescription.MyDialogDescription;
import com.android.tupple.robot.view.videolist.dialogdescription.showProgress;

import java.util.ArrayList;
import java.util.List;

public class VideoListFragment extends Fragment implements showProgress, VideoListView<Media>, DownloadInterface {


    public static final String TAG = "VideoListFragment";
    private Context mContext;
    private CleanObserver<VideoListView<Media>> mViewCreatedObserver;
    private SnapRecycleView mRecyclerViewVideo;
    private CleanObserver<Media> mItemVideoClickedObserver;
    private RecyclerViewVideoAdapter recyclerViewVideoAdapter;

    public static VideoListFragment newInstance() {
        return new VideoListFragment();
    }

    public void setViewCreatedObserver(CleanObserver<VideoListView<Media>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_video_list, container, false);
        initView(mRootView);
        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }

    private void initView(View rootView) {
        mRecyclerViewVideo = rootView.findViewById(R.id.recycler_video);
        recyclerViewVideoAdapter = new RecyclerViewVideoAdapter(mContext);
        mRecyclerViewVideo.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        mRecyclerViewVideo.setAdapter(recyclerViewVideoAdapter);
        recyclerViewVideoAdapter.setOnItemVideoClickedListener(this::handleItemVideoClicked);
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
        MyDialogDescription myDialogDescription = new MyDialogDescription(mContext);
        myDialogDescription.setActivity((Activity) mContext);
        myDialogDescription.setMedia(media);
        myDialogDescription.setVideoListFragment(this);
        myDialogDescription.show();
    }


    @Override
    public CleanObservable<Media> getItemVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemVideoClickedObserver = cleanObserver);
    }

    @Override
    public void showProgressbar(Media media) {
        recyclerViewVideoAdapter.setPositionForItemWhenStartDownload(media);
    }

    @Override
    public void showDownloadProgress(double progress) {
        recyclerViewVideoAdapter.setProgress(progress);
    }

    @Override
    public void onDownloadSuccess(String filePath, String fileName) {
        recyclerViewVideoAdapter.setPositionForItemWhenFinishDownload(fileName);
        saveUrlToDatabase();
    }

    private void saveUrlToDatabase() {
    }

    @Override
    public void onDownloadFail() {

    }
}
