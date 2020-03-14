package com.android.tupple.robot.activity;

import android.content.Intent;
import android.os.Bundle;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.base.BaseActivity;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.model.mediaobject.AudioPlayerModelFactory;
import com.android.tupple.robot.domain.entity.audioplayer.PlayAudio;
import com.android.tupple.robot.domain.presenter.audiolist.AudioListModel;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerPresenterImpl;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerView;
import com.android.tupple.robot.utils.constant.EntertainmentConstant;
import com.android.tupple.robot.view.audioplayer.AudioPlayerViewFactory;

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
        AudioListModel<Media> audioPlayerModel = AudioPlayerModelFactory.newAudioPlayerModelFactory(this);
        audioPlayerPresenter.setAudioPlayerModel(audioPlayerModel);
        audioPlayerPresenter.setAudioPlayerView(audioPlayerView);
        setCurrentAudio(bundle, audioPlayerPresenter);
        initObserver(audioPlayerPresenter);
        playAudio.setPlayAudioPresenter(audioPlayerPresenter);
        playAudio.init();
    }

    private void setCurrentAudio(Bundle bundle, AudioPlayerPresenterImpl<Media> audioPlayerPresenter) {
        Media currentAudio = bundle.getParcelable(EntertainmentConstant.AUDIO_INTENT);
        int position = bundle.getInt(EntertainmentConstant.AUDIO_POSITION);
        if (currentAudio != null) {
            audioPlayerPresenter.setCurrentAudio(currentAudio);
            audioPlayerPresenter.setCurrentPosition(position);
        }
    }

    private void initObserver(AudioPlayerPresenterImpl<Media> audioPlayerPresenter) {
        audioPlayerPresenter.setOnCloseButtonHandler(this::finish);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playAudio.finish();
    }
}
