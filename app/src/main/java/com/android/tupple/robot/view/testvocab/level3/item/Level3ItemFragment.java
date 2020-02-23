package com.android.tupple.robot.view.testvocab.level3.item;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.VisualizerView;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemFragment extends Fragment implements Level3ItemView<Vocabulary> {

    private Context mContext;

    // component
    private ImageView mBtnPronounce;
    private TextView mTextVocabulary;
    private ImageView mImageVocabulary;

    private LinearLayout mTextAnswerHeaderContainer;
    private TextView mTextYourPronounceAnswer;

    private LinearLayout mRecordingContainer;
    private VisualizerView mVisualizerViewRecording;
    private SeekBar mSeekBar;
    private TextView mTextDuration;

    // observer listener
    CleanObserver mBtnPronounceClickedObserver;

    private int mKeyView = -1;

    public static Level3ItemFragment newInstance(int keyView) {
        Level3ItemFragment fragment = new Level3ItemFragment();
        fragment.mKeyView = keyView;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_check_prounce, container, false);
        initView(rootView);

        EventBus.getDefault().post(new EventItemCreated(this, mKeyView));
        return rootView;
    }

    private void initView(View rootView) {
        mTextVocabulary = rootView.findViewById(R.id.text_vocabulary);
        mBtnPronounce = rootView.findViewById(R.id.btn_pronounce);
        mImageVocabulary = rootView.findViewById(R.id.image_vocabulary);

        mTextAnswerHeaderContainer = rootView.findViewById(R.id.text_answer_header_container);
        mTextYourPronounceAnswer = rootView.findViewById(R.id.text_your_pronounce_answer);

        mRecordingContainer = rootView.findViewById(R.id.recording_container);
        mSeekBar = rootView.findViewById(R.id.seek_bar_audio);
        mTextDuration = rootView.findViewById(R.id.text_duration);
    }

    @Override
    public void setVocabulary(Vocabulary vocabulary) {
        mTextVocabulary.setText(vocabulary.getVocabEn());
    }

    @Override
    public CleanObservable getBtnPronouneClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnPronounceClickedObserver = cleanObserver);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
