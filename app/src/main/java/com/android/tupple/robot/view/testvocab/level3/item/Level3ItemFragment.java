package com.android.tupple.robot.view.testvocab.level3.item;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.RecordState;
import com.android.tupple.robot.domain.presenter.testvocab.level3.ResultState;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;
import com.android.tupple.robot.sound.SoundPoolManagement;
import com.android.tupple.robot.utils.GlideUtils;
import com.android.tupple.robot.utils.RecordingHelper;
import com.android.tupple.robot.utils.SingleClickUtil;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

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
    private TextView mTextYourAnswer;
    private TextView mTextStateRecording;
    private ImageView mBtnRecording;

    // observer listener
    private CleanObserver mBtnPronounceClickedObserver;
    private CleanObserver<Boolean> mBtnRecordClickedObserver;
    private CleanObserver<String> mRecordStateDoneObserver;

    private RecordingHelper mRecordingHelper;
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
        checkPermission();
        initView(rootView);

        EventBus.getDefault().post(new EventItemCreated(this, mKeyView));
        return rootView;
    }

    private void checkPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRecordingHelper = new RecordingHelper(mContext);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        getActivity().finish();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    private void initView(View rootView) {
        mTextVocabulary = rootView.findViewById(R.id.text_vocabulary);
        mImageVocabulary = rootView.findViewById(R.id.image_vocabulary);

        mBtnPronounce = rootView.findViewById(R.id.btn_pronounce);
        SingleClickUtil.registerListener(mBtnPronounce, v -> {
            if (mBtnPronounceClickedObserver != null) {
                mBtnPronounceClickedObserver.onNext();
            }
        });

        mTextAnswerHeaderContainer = rootView.findViewById(R.id.text_answer_header_container);
        mTextYourAnswer = rootView.findViewById(R.id.text_your_pronounce_answer);
        mTextStateRecording = rootView.findViewById(R.id.text_state_recording);
        mBtnRecording = rootView.findViewById(R.id.btn_recording);
    }

    @Override
    public void setVocabulary(Vocabulary vocabulary) {
        mTextVocabulary.setText(vocabulary.getVocabEn());
        GlideUtils.loadImageFromStorage(mContext, vocabulary.getImageUrl(), mImageVocabulary);
    }

    @Override
    public void playPronounce(Vocabulary vocabulary) {
        SoundPoolManagement.getInstance().playSound(vocabulary.getVocabId());
    }

    @Override
    public void startRecording() {
        Toast.makeText(mContext, mContext.getString(R.string.text_state_record_start), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTextYourAnswer(String text) {
        mTextYourAnswer.setText(text);
    }

    @Override
    public void setError() {
        mTextStateRecording.setText(mContext.getString(R.string.text_state_record_error));
    }

    @Override
    public void setTextResult(ResultState state) {
        switch (state) {
            case INVALID:
                mTextYourAnswer.setText("");
                break;
            case NOT_GOOD:
                mTextStateRecording.setText(mContext.getString(R.string.text_result_not_good));
                break;
            case GOOD:
                mTextStateRecording.setText(mContext.getString(R.string.text_result_good));
                break;
            case VERY_GOOD:
                mTextStateRecording.setText(mContext.getString(R.string.text_result_very_good));
                break;
            case EXCELLENT:
                mTextStateRecording.setText(mContext.getString(R.string.text_result_excellent));
                break;
        }
    }

    @Override
    public void setStateRecording(RecordState state) {
        switch (state) {
            case WAITING:
                mBtnRecording.setBackgroundResource(R.drawable.bg_btn_record_disable);
                mTextStateRecording.setText(mContext.getString(R.string.text_state_record_done));
                break;
            case NORMAL:
            case PREPARING:
                mBtnRecording.setBackgroundResource(R.drawable.bg_btn_record);
                mTextStateRecording.setText(mContext.getString(R.string.text_state_record_prepare));
                break;
            case RECORDING:
                mBtnRecording.setBackgroundResource(R.drawable.bg_btn_record_doing);
                mTextStateRecording.setText(mContext.getString(R.string.text_state_record_doing));
                break;
        }
    }

    @Override
    public CleanObservable getBtnPronounceClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnPronounceClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<Boolean> getBtnRecordingClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnRecordClickedObserver = cleanObserver);
    }

    @Override
    public CleanObservable<String> getRecordStateDoneObservable() {
        return CleanObservable.create(cleanObserver -> mRecordStateDoneObserver = cleanObserver);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
