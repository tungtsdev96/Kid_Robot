package com.android.tupple.robot.view.testvocab.level3.item;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.R;
import com.android.tupple.robot.common.customview.VisualizerView;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;
import com.android.tupple.robot.utils.RecordingHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.tuple.kidrobot.ripplerecordingview.Rate;
import com.tuple.kidrobot.ripplerecordingview.VoiceRippleView;
import com.tuple.kidrobot.ripplerecordingview.renderer.Renderer;
import com.tuple.kidrobot.ripplerecordingview.renderer.TimerCircleRippleRenderer;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

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
    private ImageView btnRecording;

    private VoiceRippleView mVoiceRippleView;

    // observer listener
    private CleanObserver mBtnPronounceClickedObserver;

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
        mBtnPronounce = rootView.findViewById(R.id.btn_pronounce);
        mImageVocabulary = rootView.findViewById(R.id.image_vocabulary);

        mTextAnswerHeaderContainer = rootView.findViewById(R.id.text_answer_header_container);
        mTextYourPronounceAnswer = rootView.findViewById(R.id.text_your_pronounce_answer);

        mRecordingContainer = rootView.findViewById(R.id.recording_container);
        mVisualizerViewRecording = rootView.findViewById(R.id.visualizer_view_recording);
        mSeekBar = rootView.findViewById(R.id.seek_bar_audio);
        mTextDuration = rootView.findViewById(R.id.text_duration);

        mVoiceRippleView = rootView.findViewById(R.id.voice_ripple_view);

        // set view related settings for ripple view
        mVoiceRippleView.setRippleSampleRate(Rate.LOW);
        mVoiceRippleView.setRippleDecayRate(Rate.HIGH);
        mVoiceRippleView.setBackgroundRippleRatio(1.4);

        File temp;
        File cacheRecording = new File(mContext.getCacheDir(),"record");
        if (!cacheRecording.exists()) {
            boolean isSuccess = cacheRecording.mkdir();
        }

        temp = new File(cacheRecording + "/tungts.mp3");

        // set recorder related settings for ripple view
        mVoiceRippleView.setMediaRecorder(new MediaRecorder());
        mVoiceRippleView.setOutputFile(temp.getAbsolutePath());
        mVoiceRippleView.setAudioSource(MediaRecorder.AudioSource.MIC);
        mVoiceRippleView.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mVoiceRippleView.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        // set inner icon for record and recording
        mVoiceRippleView.setRecordDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_recording), ContextCompat.getDrawable(mContext, R.drawable.ic_recording));
        mVoiceRippleView.setIconSize(30);

        // change recording status when clicked
        mVoiceRippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mVoiceRippleView.isRecording()) {
                    mVoiceRippleView.stopRecording();
                } else {
                    mVoiceRippleView.startRecording();
                }
            }
        });

        Renderer currentRenderer = new TimerCircleRippleRenderer(getDefaultRipplePaint(), getDefaultRippleBackgroundPaint(), getButtonPaint(), getArcPaint(), 10000,0.0);
        if (currentRenderer instanceof TimerCircleRippleRenderer) {
            ((TimerCircleRippleRenderer) currentRenderer).setStrokeWidth(20);
        }

        mVoiceRippleView.setRenderer(currentRenderer);
    }

    private Paint getArcPaint() {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(mContext, R.color.color1));
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
    private Paint getDefaultRipplePaint() {
        Paint ripplePaint = new Paint();
        ripplePaint.setStyle(Paint.Style.FILL);
        ripplePaint.setColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        ripplePaint.setAntiAlias(true);

        return ripplePaint;
    }

    private Paint getDefaultRippleBackgroundPaint() {
        Paint rippleBackgroundPaint = new Paint();
        rippleBackgroundPaint.setStyle(Paint.Style.FILL);
        rippleBackgroundPaint.setColor((ContextCompat.getColor(mContext, R.color.colorPrimary) & 0x00FFFFFF) | 0x40000000);
        rippleBackgroundPaint.setAntiAlias(true);

        return rippleBackgroundPaint;
    }

    private Paint getButtonPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    public void setVocabulary(Vocabulary vocabulary) {
        mTextVocabulary.setText(vocabulary.getVocabEn());
    }

    @Override
    public CleanObservable getBtnPronounceClickedObservable() {
        return CleanObservable.create(cleanObserver -> mBtnPronounceClickedObserver = cleanObserver);
    }

    @Override
    public void onStop() {
        super.onStop();
        RecordingHelper.newInstance(mContext).stop();
    }
}
