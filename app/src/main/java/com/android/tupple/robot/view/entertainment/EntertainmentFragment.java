package com.android.tupple.robot.view.entertainment;

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
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;

public class EntertainmentFragment extends Fragment implements EntertainmentView {
    public static final String TAG = "EntertainmentFragment";
    private Context mContext;

    private CleanObserver<EntertainmentView> mViewCreatedObserver;

    public void setViewCreatedObserver(CleanObserver<EntertainmentView> viewCreatedObserver) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_entertainment, container, false);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return mRootView;
    }

}
