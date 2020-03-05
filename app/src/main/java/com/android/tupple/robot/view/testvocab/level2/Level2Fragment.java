package com.android.tupple.robot.view.testvocab.level2;

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
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2View;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level2Fragment extends Fragment implements Level2View<LessonData, Topic, Vocabulary> {

    public final static String TAG = "Level2Fragment";

    private Context mContext;

    private LinearAlphabetView mQuestionView;
    private LinearAlphabetView mAnswerView;


    private CleanObserver<Level2View<LessonData, Topic, Vocabulary>> mViewCreatedObserver;

    public static Level2Fragment newInstance() {
        return new Level2Fragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_vocab_level_2, container, false);

        initView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initView(View rootView) {
        mQuestionView = rootView.findViewById(R.id.alphabet_question);
        mAnswerView = rootView.findViewById(R.id.alphabet_answer);
    }

    public void setViewCreatedObserver(CleanObserver<Level2View<LessonData, Topic, Vocabulary>> onViewCreatedObserver) {
        this.mViewCreatedObserver = onViewCreatedObserver;
    }

    @Override
    public void showQuestion(char[] question) {
        if (mQuestionView != null) {
            mQuestionView.generateView(question);
        }
    }

    @Override
    public void showAnswer(char[] answer) {
        if (mAnswerView != null) {
            mAnswerView.generateView(answer);
        }
    }

}
