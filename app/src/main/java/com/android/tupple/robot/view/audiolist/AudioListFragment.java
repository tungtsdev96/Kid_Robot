package com.android.tupple.robot.view.audiolist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.android.tupple.robot.domain.presenter.audiolist.AudioListView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListView;
import com.android.tupple.robot.view.videolist.RecyclerViewVideoAdapter;

import java.util.List;

public class AudioListFragment extends Fragment implements AudioListView<Media> {
    public static final String TAG = "AudioListFragment";
    private Context mContext;
    private CleanObserver<AudioListView<Media>> mViewCreatedObserver;
    private SnapRecycleView mRecyclerViewAudio;
    private CleanObserver<Media> mItemAudioClickedObserver;
    private RecyclerViewAudioAdapter recyclerViewAudioAdapter;

    public void setViewCreatedObserver(CleanObserver<AudioListView<Media>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    public static AudioListFragment newInstance() {
        return new AudioListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_audio_list, container, false);
        initView(mRootView);
        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }

    private void handleItemAudioClicked(int position) {
        if (mItemAudioClickedObserver != null) {
            mItemAudioClickedObserver.onNext(recyclerViewAudioAdapter.getAudioByPosition(position));
        }
    }

    private void initView(View rootView) {
        mRecyclerViewAudio = rootView.findViewById(R.id.recycler_audio);
        recyclerViewAudioAdapter = new RecyclerViewAudioAdapter(mContext);
        mRecyclerViewAudio.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        mRecyclerViewAudio.setAdapter(recyclerViewAudioAdapter);
        recyclerViewAudioAdapter.setOnItemAudioClickedListener(this::handleItemAudioClicked);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setListAudio(List<Media> listMedia) {
        recyclerViewAudioAdapter.setListData(listMedia);
    }

    @Override
    public CleanObservable<Media> getItemAudioClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemAudioClickedObserver = cleanObserver);
    }
}
