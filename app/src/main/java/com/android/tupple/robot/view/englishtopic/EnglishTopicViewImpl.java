package com.android.tupple.robot.view.englishtopic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.PresenterObserver;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicViewImpl implements EnglishTopicView<Topic> {
    private Activity mActivity;
    private Bundle mBundle;

    private SnapRecycleView mRcvTopic;
    private TopicAdapter mTopicAdapter;
    private FloatingActionButton mBtnClose;
    private CleanObserver<Topic> mItemTopicClickedObserver;
    private CleanObserver mButtonCloseClickedObserver;

    public EnglishTopicViewImpl(Activity mActivity, Bundle mBundle) {
        this.mActivity = mActivity;
        this.mBundle = mBundle;
    }

    @Override
    public void initLayout() {
        initRecycleView(mActivity);
    }

    @Override
    public void setListTopic(List<Topic> listTopic) {
        mTopicAdapter.setListData(listTopic);
    }


    @Override
    public CleanObservable<Topic> getItemTopicClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemTopicClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getButtonCloseClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonCloseClickedObserver = cleanObserver);
    }


    private void initRecycleView(Activity activity) {
        mRcvTopic = activity.findViewById(R.id.rcv_topic);
        mTopicAdapter = new TopicAdapter(mActivity);
        mRcvTopic.setLayoutManager(new GridLayoutManager(mActivity, 2, RecyclerView.HORIZONTAL, false));
        mRcvTopic.setAdapter(mTopicAdapter);
        mTopicAdapter.setOnItemTopicClickedListener(this::handleItemTopicClicked);
        mBtnClose = activity.findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonCloseClickedObserver != null)
                    mButtonCloseClickedObserver.onNext();
            }
        });
    }

    private void handleItemTopicClicked(int position) {
        if (mItemTopicClickedObserver != null) {
            mItemTopicClickedObserver.onNext(mTopicAdapter.getTopicByPosition(position));
        }
    }


}
