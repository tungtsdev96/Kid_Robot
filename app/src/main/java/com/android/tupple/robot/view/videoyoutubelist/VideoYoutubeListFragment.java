package com.android.tupple.robot.view.videoyoutubelist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListView;

import java.util.List;

public class VideoYoutubeListFragment extends Fragment implements VideoYoutubeListView<Media> {

    public static final String TAG = "VideoYoutubeListFragment";
    private CleanObserver<VideoYoutubeListView<Media>> mViewCreatedObserver;
    private Context mContext;
    private SnapRecycleView mRecyclerViewVideoYoutube;
    private CleanObserver<Media> mItemVideoYoutubeClickedObserver;
    private RecyclerViewVideoYoutubeApdater recyclerViewVideoYoutubeAdapter;

    public static VideoYoutubeListFragment newInstance() {
        return new VideoYoutubeListFragment();
    }

    public void setViewCreatedObserver(CleanObserver<VideoYoutubeListView<Media>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_video_youtube_list, container, false);
        initView(mRootView);
        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }

    private void initView(View rootView) {
        mRecyclerViewVideoYoutube = rootView.findViewById(R.id.recycler_video);
        recyclerViewVideoYoutubeAdapter = new RecyclerViewVideoYoutubeApdater(mContext);
        mRecyclerViewVideoYoutube.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        mRecyclerViewVideoYoutube.setAdapter(recyclerViewVideoYoutubeAdapter);
        recyclerViewVideoYoutubeAdapter.setOnItemVideoClickedListener(this::handleItemVideoClicked);
    }

    private void handleItemVideoClicked(int position) {
        if (mItemVideoYoutubeClickedObserver != null) {
            mItemVideoYoutubeClickedObserver.onNext(recyclerViewVideoYoutubeAdapter.getVideoByPosition(position));
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void initLayout() {

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
        return null;
    }
}
