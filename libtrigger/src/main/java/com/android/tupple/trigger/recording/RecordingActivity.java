package com.android.tupple.trigger.recording;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.android.tupple.trigger.R;
import com.android.tupple.trigger.TriggerHelper;
import com.android.tupple.trigger.TriggerService;
import com.android.tupple.trigger.utils.TriggerConstant;
import com.android.tupple.trigger.utils.WindowManagerUtils;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;

import java.util.ArrayList;

/**
 * Created by tungts on 2020-02-01.
 * TODO recording audio and send to server
 */

public class RecordingActivity extends Activity {

    private RecognitionProgressView mRecognitionProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.setFullScreenMode(this);
        setContentView(R.layout.activity_recording);

        initRecognize();
    }

    private void initRecognize() {
        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };

        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        mRecognitionProgressView = findViewById(R.id.recognition_view);
        mRecognitionProgressView.setColors(colors);
        mRecognitionProgressView.play();

        mRecognitionProgressView.setSpeechRecognizer(speechRecognizer);
        mRecognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                handleResultRecognize(result != null ? result.get(0) : null);
            }

            @Override
            public void onError(int error) {
                super.onError(error);
                handleErrorRecognize(error);
            }

            @Override
            public void onEndOfSpeech() {
                super.onEndOfSpeech();
                mRecognitionProgressView.stop();
                finish();
            }
        });

        startRecognize(speechRecognizer);
    }

    private void handleErrorRecognize(int error) {
        switch (error) {
            case SpeechRecognizer.ERROR_NETWORK:
                finish();
                break;
        }
    }

    private void handleResultRecognize(String result) {
        mRecognitionProgressView.stop();
        Intent intent = new Intent();
        intent.setAction(TriggerConstant.ACTION_RECOGNIZE_DONE);
        intent.putExtra(TriggerConstant.EXTRA_RECOGNIZE_DONE, result);
        sendBroadcast(intent);
        Log.d(TriggerService.TAG, result);
    }

    private void startRecognize(SpeechRecognizer speechRecognizer) {
        Intent intent = new Intent(RecognizerIntent.ACTION_GET_LANGUAGE_DETAILS);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "vi-VN");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);
        speechRecognizer.startListening(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TriggerHelper.stopTrigger(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        TriggerHelper.startTrigger(this);
    }
}
