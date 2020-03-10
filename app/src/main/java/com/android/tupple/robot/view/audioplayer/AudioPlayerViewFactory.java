package com.android.tupple.robot.view.audioplayer;

import android.app.Activity;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.audioplayer.AudioPlayerView;

public class AudioPlayerViewFactory {
    public static AudioPlayerView<Media> newAudioPlayerView(Activity activity){
        return new AudioPlayerViewImpl(activity);
    }
}
