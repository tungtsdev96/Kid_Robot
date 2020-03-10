package com.android.tupple.robot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.data.model.mediaobject.AudioPlayerModelFactory;
import com.android.tupple.robot.domain.entity.audioplayer.PlayAudio;
import com.android.tupple.robot.domain.entity.learnvocab.LearnVocab;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerPresenterImpl;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerView;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabPresenterImpl;
import com.android.tupple.robot.view.audioplayer.AudioPlayerViewFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayerActivity extends BaseActivity {
    private ActivityLauncher activityLauncher;
    private PlayAudio playAudio;

    @Override
    protected int getLayoutContent() {
        return R.layout.activity_audio_player;
    }

    @Override
    protected void onCreatedActivity(Bundle savedInstanceState) {
        Bundle bundle = null;
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        injectFirstBatch();
        inject(bundle);
    }

    private void injectFirstBatch() {
        activityLauncher = new ActivityLauncher(this);
        playAudio = new PlayAudio();
    }

    private void inject(Bundle bundle) {
        AudioPlayerPresenterImpl<Media> audioPlayerPresenter = new AudioPlayerPresenterImpl<>();
        AudioPlayerView<Media> audioPlayerView = AudioPlayerViewFactory.newAudioPlayerView(this);
        EntertainmentModel<Media> audioPlayerModel = AudioPlayerModelFactory.newAudioPlayerModelFactory(this);
        audioPlayerPresenter.setAudioPlayerModel(audioPlayerModel);
        audioPlayerPresenter.setAudioPlayerView(audioPlayerView);
        setCurrentAudio(bundle, audioPlayerPresenter);
        initObserver(audioPlayerPresenter);
        playAudio.setPlayAudioPresenter(audioPlayerPresenter);
        playAudio.init();
    }

    private void setCurrentAudio(Bundle bundle, AudioPlayerPresenterImpl<Media> audioPlayerPresenter) {
        Media currentAudio = bundle.getParcelable("currentAudio");
        audioPlayerPresenter.setCurrentAudio(currentAudio);
    }

    private void initObserver(AudioPlayerPresenterImpl<Media> audioPlayerPresenter) {
        audioPlayerPresenter.setOnCloseButtonHandler(this::onBackPressed);

    }
}
