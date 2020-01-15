package com.android.tupple.robot.view.englishtopic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.commondata.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishTopicFragment extends Fragment implements EnglishTopicView<Topic> {

    public static final String TAG = "EnglishTopicFragment";

    private Context mContext;

    private CleanObserver<EnglishTopicView<Topic>> mViewCreatedObserver;

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
        View mRootView = inflater.inflate(R.layout.fragment_english_topic, container, false);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }


}
