package com.android.tupple.robot.view.testvocab.level3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.Level3View;
import com.android.tupple.robot.view.testvocab.level3.item.Level3ItemFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Created by tungts on 2020-02-20.
 */

public class Level3Fragment extends Fragment implements Level3View<LessonData, Topic, Vocabulary> {

    public final static String TAG = "Level3Fragment";

    private Context mContext;

    private FloatingActionButton mFabPrevious;
    private FloatingActionButton mFabNext;

    private ViewPager2 mViewpagerCheckProunce;
    private CheckPronouncePagerAdapter mCheckPronouncePagerAdapter;

    private CleanObserver<Level3View<LessonData, Topic, Vocabulary>> mViewCreatedObserver;
    private CleanObserver mBtnPreviousClickedObserver;
    private CleanObserver mBtnNextClickedObserver;
    private CleanObserver mBtnRecordingClickedObserver;

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

        initView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    public void setViewCreatedObserver(CleanObserver<Level3View<LessonData, Topic, Vocabulary>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    private void initView(View rootView) {
        mFabPrevious = rootView.findViewById(R.id.btn_previous_vocab);
        mFabNext = rootView.findViewById(R.id.btn_next_vocab);

        mViewpagerCheckProunce = rootView.findViewById(R.id.view_pager_check_pronounce);
        mCheckPronouncePagerAdapter = new CheckPronouncePagerAdapter(this);
        mViewpagerCheckProunce.setOffscreenPageLimit(3);
        mViewpagerCheckProunce.setUserInputEnabled(false);
        mViewpagerCheckProunce.setAdapter(mCheckPronouncePagerAdapter);
    }

    @Override
    public void setListLearningVocab(List<Vocabulary> vocabularies) {
        if (mCheckPronouncePagerAdapter != null) {
            for (Vocabulary vocabulary: vocabularies) {
                mCheckPronouncePagerAdapter.addItem(Level3ItemFragment.newInstance(vocabulary.getVocabId()));
            }
            mCheckPronouncePagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public CleanObservable getBtnPreviousClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnPreviousClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getBtnNextClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnNextClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getBtnRecordingClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnRecordingClickedObserver = cleanObserver);
    }
}
