package com.android.tupple.robot.view.entertainment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.snaprecycleview.SnapRecycleView;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.VideoListModelFactory;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentView;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListPresenterImpl;
import com.android.tupple.robot.domain.presenter.videolist.VideoListViewWrapper;
import com.android.tupple.robot.view.audiolist.AudioListFragment;
import com.android.tupple.robot.view.lesson.LessonFragment;
import com.android.tupple.robot.view.videolist.VideoListFragment;
import com.android.tupple.robot.view.videolist.VideoListViewWrapperFactory;

import java.util.List;

public class EntertainmentFragment extends Fragment implements EntertainmentView<Fragment> {
    public static final String TAG = "EntertainmentFragment";
    private static FragmentManager mFragmentManager;
    private Context mContext;


    private CleanObserver<EntertainmentView<Fragment>> mViewCreatedObserver;


    private RadioButton mBtnAudio, mBtnVideo;
    private CleanObserver<Fragment> mButtonVideoClickedObserver;
    private CleanObserver<Fragment> mButtonAudioClickedObserver;

    public void setViewCreatedObserver(CleanObserver<EntertainmentView<Fragment>> viewCreatedObserver) {
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
        mBtnAudio = rootView.findViewById(R.id.btn_tab_audio);
        mBtnVideo = rootView.findViewById(R.id.btn_tab_video);
        mBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click video", Toast.LENGTH_SHORT).show();
                mButtonVideoClickedObserver.onNext(new VideoListFragment());
            }
        });
        mBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click audio", Toast.LENGTH_SHORT).show();
                mButtonAudioClickedObserver.onNext(new AudioListFragment());
            }
        });
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
    public void setTitleHeader(Fragment fragment) {

    }

    @Override
    public void setCurrentFragment(Fragment fragment) {

    }

    @Override
    public CleanObservable<Fragment> getButtonVideoClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonVideoClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<Fragment> getButtonAudioClickedObservable() {
        return CleanObservable.create(cleanObserver -> mButtonAudioClickedObserver = cleanObserver);
    }

}
