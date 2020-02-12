package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.android.tupple.robot.R;
import com.android.tupple.robot.base.BaseActivity;
import com.android.tupple.robot.common.customview.VisualizerView;


public class AudioRecorderActivity extends BaseActivity implements View.OnClickListener {

    private ActivityLauncher mActivityLauncher;
    private Handler handler;
    public static final int REPEAT_INTERVAL = 40;
    private boolean isRecording = false;
    MediaRecorder recorder;
    VisualizerView visualizerView;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_audio_recorder;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        mActivityLauncher = new ActivityLauncher(this);
        checkPermission();
        handler = new Handler();
        findViewById(R.id.recoder).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.recoder: {
                startRecoder("test12345");
                break;
            }
        }
    }

    private void startRecoder(String fileName) {
        isRecording = true;
        recorder = new MediaRecorder();
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.MediaColumns.TITLE, fileName);
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile("/sdcard/log/" + fileName + ".mp3");
        try {

            recorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Recoder");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_audio_recorder);
        visualizerView = dialog.findViewById(R.id.visualizer);
        dialog.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(AudioRecorderActivity.this, "Finish Recoding", Toast.LENGTH_SHORT).show();
                isRecording = false;
                recorder.stop();
                recorder.release();
                handler.removeCallbacks(updateVisualizer);
                visualizerView.clear();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isRecording = false;
                recorder.stop();
                recorder.release();
                handler.removeCallbacks(updateVisualizer);
                visualizerView.clear();
            }
        });
        recorder.start();
        handler.post(updateVisualizer);
        dialog.show();
    }

    void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }
    }

    Runnable updateVisualizer = new Runnable() {
        @Override
        public void run() {
            if (isRecording)
            {
                // get the current amplitude
                int x = recorder.getMaxAmplitude();
                visualizerView.addAmplitude(x); // update the VisualizeView
                visualizerView.invalidate(); // refresh the VisualizerView
                // update in 40 milliseconds
                handler.postDelayed(this, REPEAT_INTERVAL);
            }
        }
    };
}