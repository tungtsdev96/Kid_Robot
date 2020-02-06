package com.android.tupple.robot.view.testvocab.level1;

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
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1View;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.level1.item.AnswerItem;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1Fragment extends Fragment implements Level1View<LessonData, Topic, Vocabulary> {

    public final static String TAG = "Level1Fragment";

    private Context mContext;

    private RecyclerView mRcvAnswer;
    private AnswerAdapter mAnswerAdapter;

    private CleanObserver<Level1View<LessonData, Topic, Vocabulary>> mViewCreatedObserver;
    private CleanObserver<Boolean> mChooseAnswerObserver;

    public static Level1Fragment newInstance() {
        return new Level1Fragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_vocab_level_1, container, false);

        initRecycleView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initRecycleView(View rootView) {
        mRcvAnswer = rootView.findViewById(R.id.rcv_answer_level_1);
        mAnswerAdapter = new AnswerAdapter(mContext);
        mRcvAnswer.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRcvAnswer.setAdapter(mAnswerAdapter);
        mAnswerAdapter.setOnAnswerClickListener(position -> {
            AnswerItem item = mAnswerAdapter.getAnswerItemByPosititon(position);
            if (mChooseAnswerObserver != null) {
                mChooseAnswerObserver.onNext(item.isAnswerTrue());
            }
        });
    }

    public void setViewCreatedObserver(CleanObserver<Level1View<LessonData, Topic, Vocabulary>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    @Override
    public void setData(List<Vocabulary> items) {
        mAnswerAdapter.setListAnswer(items, 0, TestVocabConstant.ANSWER_TYPE.TEXT_AND_IMAGE);
    }

    @Override
    public CleanObservable<Boolean> getAnswerChooseObservable() {
        return CleanObservable.create(cleanObserver -> mChooseAnswerObserver = cleanObserver);
    }

}
