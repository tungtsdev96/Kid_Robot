package com.android.tupple.robot.view.testvocab.level3;

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
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3View;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3Fragment extends Fragment implements Level3View<LessonData, Topic, Vocabulary> {

    public final static String TAG = "Level3Fragment";

    private Context mContext;

    private CleanObserver<Level3View<LessonData, Topic, Vocabulary>> mViewCreatedObserver;

    public static Level3Fragment newInstance() {
        return new Level3Fragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_vocab_level_3, container, false);

//        initView(rootView);
//        initRecycleView(rootView);
//
        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    public void setViewCreatedObserver(CleanObserver<Level3View<LessonData, Topic, Vocabulary>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }
}
