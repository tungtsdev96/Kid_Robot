package com.android.tupple.trigger.recording;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.android.tupple.trigger.R;
import com.android.tupple.trigger.TriggerHelper;
import com.android.tupple.trigger.utils.WindowManagerUtils;
import com.github.zagum.speechrecognitionview.RecognitionProgressView;
import com.github.zagum.speechrecognitionview.adapters.RecognitionListenerAdapter;

import java.util.ArrayList;

/**
 * Created by tungts on 2020-02-01.
 */

public class RecordingActivity extends Activity {

    private RecognitionProgressView mRecognitionProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManagerUtils.setFullScreenMode(this);
        setContentView(R.layout.activity_recording);

        init();
    }

    private void init() {
        SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        mRecognitionProgressView = findViewById(R.id.recognition_view);
        mRecognitionProgressView.play();

        mRecognitionProgressView.setSpeechRecognizer(speechRecognizer);
        mRecognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
                // TODO show result
                ArrayList<String> matches = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Toast.makeText(RecordingActivity.this, matches.get(0), Toast.LENGTH_LONG).show();

                mRecognitionProgressView.stop();
            }

            @Override
            public void onError(int error) {
                super.onError(error);
//                startRecognize(speechRecognizer);
                Toast.makeText(RecordingActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEndOfSpeech() {
                super.onEndOfSpeech();
                Toast.makeText(RecordingActivity.this, "End Speed", Toast.LENGTH_SHORT).show();
                mRecognitionProgressView.stop();
                finish();
            }
        });


        startRecognize(speechRecognizer);
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
