package com.android.tupple.robot.view.lesson;

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
import com.android.tupple.robot.common.data.LessonData;
import com.android.tupple.robot.domain.presenter.lesson.LessonView;

import java.util.List;

/**
 * Created by tungts on 2020-01-18.
 */

public class LessonFragment extends Fragment implements LessonView<LessonData>, LessonAdapter.ItemLessonListener {

    public static final String TAG = "LessonFragment";

    private Context mContext;
    private CleanObserver<LessonView<LessonData>> mViewCreatedObserver;
    private CleanObserver<LessonData> mItemLessonClickedObserver;

    private RecyclerView mRcvLesson;
    private LessonAdapter mLessonAdapter;

    static LessonFragment newInstance() {
        return new LessonFragment();
    }

    void setViewCreatedObserver(CleanObserver<LessonView<LessonData>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lesson, container, false);

        initView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initView(View rootView) {
        mRcvLesson = rootView.findViewById(R.id.rcv_lesson);
        mLessonAdapter = new LessonAdapter(mContext);
        mRcvLesson.setLayoutManager(new GridLayoutManager(mContext, 4, RecyclerView.VERTICAL, false));
        mRcvLesson.setAdapter(mLessonAdapter);
        mLessonAdapter.setOnItemLessonListener(this);
    }

    @Override
    public void setDataList(List<LessonData> listLessonData) {
        mLessonAdapter.setListData(listLessonData);
    }

    @Override
    public void onClick(int position) {
        if (mItemLessonClickedObserver != null) {
            mItemLessonClickedObserver.onNext(mLessonAdapter.getLessonByPosition(position));
        }
    }

    @Override
    public void onLongClick(int position) {

    }

    @Override
    public CleanObservable<LessonData> getItemLessonClickedObservable() {
        return CleanObservable.create(cleanObserver -> mItemLessonClickedObserver = cleanObserver);
    }

}
