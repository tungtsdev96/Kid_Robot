package com.android.tupple.robot.view.englishtopic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;

import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicFragment extends Fragment implements EnglishTopicView<Topic> {

    public static final String TAG = "EnglishTopicFragment";

    private Context mContext;

    private SnapRecycleView mRcvTopic;
    private TopicAdapter mTopicAdapter;

    private CleanObserver<EnglishTopicView<Topic>> mViewCreatedObserver;
    private CleanObserver<Topic> mItemTopicClickedObserver;

    public void setViewCreatedObserver(CleanObserver<EnglishTopicView<Topic>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    public static EnglishTopicFragment newInstance() {
        return new EnglishTopicFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_english_topic, container, false);

        initRecycleView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initRecycleView(View rootView) {
        mRcvTopic = rootView.findViewById(R.id.rcv_topic);
        mTopicAdapter = new TopicAdapter(mContext);
        mRcvTopic.setLayoutManager(new GridLayoutManager(mContext, 2, RecyclerView.HORIZONTAL, false));
        mRcvTopic.setAdapter(mTopicAdapter);
        mTopicAdapter.setOnItemTopicClickedListener(this::handleItemTopicClicked);
    }

    private void handleItemTopicClicked(int position) {
        if (mItemTopicClickedObserver != null) {
            mItemTopicClickedObserver.onNext(mTopicAdapter.getTopicByPosition(position));
        }
    }

    @Override
    public void setListTopic(List<Topic> listTopic) {
        mTopicAdapter.setListData(listTopic);
    }

    @Override
    public CleanObservable<Topic> getItemTopicClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemTopicClickedObserver = cleanObserver);
    }
}
