package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class VideoModelImpl implements EntertainmentModel<Media> {
    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public VideoModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public CleanObservable<List<Media>> getAllVideo() {
        return CleanObservable.create(
                cleanObserver -> {
                    cleanObserver.onNext(Media.fakeVideoData());
                }
        );
    }

    @Override
    public CleanObservable<List<Media>> getAllAudio() {
        return CleanObservable.create(
                cleanObserver -> {
                    cleanObserver.onNext(Media.fakeAudioData());
                }
        );
    }
}
