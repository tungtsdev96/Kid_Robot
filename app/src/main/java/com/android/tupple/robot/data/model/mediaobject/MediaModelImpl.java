package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MediaModelImpl implements VideoListModel<Media>, AudioListModel<Media>, VideoYoutubeListModel<Media> {
    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MediaModelImpl(Context mContext) {
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


    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public CleanObservable<List<Media>> getAllVideoYoutube() {
        return CleanObservable.create(
                cleanObserver -> {
                    cleanObserver.onNext(Media.fakeVideoYoutubeData());
                }
        );
    }
}
