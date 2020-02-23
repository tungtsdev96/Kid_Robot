package com.android.tupple.robot.view.entertainment;

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
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Video;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;

import java.util.List;

public class EntertainmentFragment extends Fragment implements EntertainmentView<Video> {
    public static final String TAG = "EntertainmentFragment";
    private Context mContext;

    private CleanObserver<EntertainmentView<Video>> mViewCreatedObserver;
    private RecyclerView mRecyclerViewVideo;
    private RecyclerView mRecyclerViewAudio;
    private CleanObserver<Video> mItemVideoClickedObserver;
    private RecyclerViewVideoAdapter recyclerViewVideoAdapter;

    public void setViewCreatedObserver(CleanObserver<EntertainmentView<Video>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    public static EntertainmentFragment newInstance() {
        return new EntertainmentFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_entertainment, container, false);
        initView(mRootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }

    @Override
    public void setListVideo(List listVideo) {
        recyclerViewVideoAdapter.setListData(listVideo);
    }

    @Override
    public CleanObservable<Video> getItemVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemVideoClickedObserver = cleanObserver);
    }
}
