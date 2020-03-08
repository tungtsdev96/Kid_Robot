package com.android.tupple.robot.view.testvocab.level1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.android.tupple.robot.domain.entity.testvocab.TestVocabLevel;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1View;
import com.android.tupple.robot.sound.SoundPoolManagement;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.android.tupple.robot.utils.constant.TestVocabConstant;
import com.android.tupple.robot.view.testvocab.adapter.AnswerAdapter;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public class Level1Fragment extends Fragment implements Level1View<LessonData, Topic, Vocabulary> {

    public final static String TAG = "Level1Fragment";

    private Context mContext;

    private TextView mTextQuestionAnswer;
    private ImageView mImageQuestionAnswer;
    private ButtonCheckAnswer mBtnCheckAnswer;

    private RecyclerView mRcvAnswer;
    private AnswerAdapter mAnswerAdapter;

    private CleanObserver<Level1View<LessonData, Topic, Vocabulary>> mViewCreatedObserver;
    private CleanObserver<Integer> mAnswerSelectedObserver;
    private CleanObserver<Boolean> mBtnCheckAnswerClickedObserver;
    private CleanObserver mBtnPronounceVocabClicked;

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

        initView(rootView);
        initRecycleView(rootView);

        if (mViewCreatedObserver != null) {
            mViewCreatedObserver.onNext(this);
        }
        return rootView;
    }

    private void initView(View rootView) {
        ImageView mButtonPronounce = rootView.findViewById(R.id.btn_pronounce);
        mButtonPronounce.setOnClickListener(v -> {
            if (mBtnPronounceVocabClicked != null) {
                mBtnPronounceVocabClicked.onNext();
            }
        });

        mTextQuestionAnswer = rootView.findViewById(R.id.text_question_vocab);
        mImageQuestionAnswer = rootView.findViewById(R.id.image_question_vocab);

        mBtnCheckAnswer = rootView.findViewById(R.id.btn_check_answer);
        SingleClickUtil.registerListener(mBtnCheckAnswer, this::handleBtnCheckAnswer);
    }

    private void handleBtnCheckAnswer(View view) {
        if (mBtnCheckAnswerClickedObserver != null) {
            mBtnCheckAnswerClickedObserver.onNext(mBtnCheckAnswer.isCheck());
        }
    }

    private void initRecycleView(View rootView) {
        mRcvAnswer = rootView.findViewById(R.id.rcv_answer_level_1);
        mAnswerAdapter = new AnswerAdapter(mContext);
        mRcvAnswer.setLayoutManager(new GridLayoutManager(mContext, 4));
        mRcvAnswer.setAdapter(mAnswerAdapter);
        mAnswerAdapter.setOnAnswerClickListener(pos -> {
            if (mAnswerSelectedObserver != null) {
                mAnswerSelectedObserver.onNext(pos);
            }
        });
    }

    public void setViewCreatedObserver(CleanObserver<Level1View<LessonData, Topic, Vocabulary>> viewCreatedObserver) {
        this.mViewCreatedObserver = viewCreatedObserver;
    }

    @Override
    public void playAudioVocab(Vocabulary question) {
        SoundPoolManagement.getInstance().playSound(question.getVocabId());
    }

    @Override
    public void showQuestion(TestVocabLevel testVocabLevel, Vocabulary vocabulary, List<Vocabulary> listAnswers) {
        mBtnCheckAnswer.setSate(true);
        mBtnCheckAnswer.setText(mContext.getString(R.string.text_check_answer));

        switch (testVocabLevel) {
            default:
            case LEVEL1_1:
                mTextQuestionAnswer.setVisibility(View.VISIBLE);
                mImageQuestionAnswer.setVisibility(View.GONE);
                mTextQuestionAnswer.setText(vocabulary.getVocabEn());
                mAnswerAdapter.setListAnswer(listAnswers, TestVocabConstant.ANSWER_TYPE.IMAGE);
                break;
            case LEVEL1_2:
                mTextQuestionAnswer.setVisibility(View.GONE);
                mImageQuestionAnswer.setVisibility(View.VISIBLE);
                GlideUtils.loadImageFromStorage(mContext, vocabulary.getImageUrl(), mImageQuestionAnswer);
                mAnswerAdapter.setListAnswer(listAnswers, TestVocabConstant.ANSWER_TYPE.TEXT);
                break;
        }
    }

    @Override
    public void notifyMustSelectedAnswer() {
        Toast.makeText(mContext, "Selected Answer before", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEnableBtnCheckAnswer(boolean isSelected) {
        mBtnCheckAnswer.setEnabled(isSelected);
        mBtnCheckAnswer.setTextColor(isSelected ? getResources().getColor(R.color.text_btn_answer_enable) : getResources().getColor(R.color.text_btn_answer_disable));
        mBtnCheckAnswer.setBackground(isSelected ? getResources().getDrawable(R.drawable.bg_btn_check_answer_enable) : getResources().getDrawable(R.drawable.bg_btn_check_answer_disable));
    }

    @Override
    public void setAnswerSelected(int answerSelected) {
        mAnswerAdapter.updateAnswerSelected(answerSelected);
    }

    @Override
    public void showLayoutAnswerResult(boolean isRight, int resultPosition) {
        Toast.makeText(mContext, isRight ? mContext.getString(R.string.header_answer_right) : mContext.getString(R.string.header_answer_wrong), Toast.LENGTH_LONG).show();
        mBtnCheckAnswer.setSate(false);
        mBtnCheckAnswer.setText(mContext.getString(R.string.text_continue));
        mBtnCheckAnswer.setBackgroundResource(isRight ? R.drawable.bg_btn_continue_result_true : R.drawable.bg_btn_continue_result_false);
        mAnswerAdapter.updateAnswer(isRight, resultPosition);
    }

    @Override
    public void hideLayoutAnswerResult() {
//        Toast.makeText(mContext, "Hide", Toast.LENGTH_SHORT).show();
    }

    @Override
    public CleanObservable<Integer> getAnswerSelectedObservable() {
        return CleanObservable.create(cleanObserver -> mAnswerSelectedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<Boolean> getBtnCheckAnswerClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnCheckAnswerClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable getBtnPronounceVocabClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnPronounceVocabClicked = cleanObserver);
    }

}
