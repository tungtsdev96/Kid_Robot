package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.MediaDao;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.domain.presenter.listaudio.AudioListModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;
import com.android.tupple.robot.domain.presenter.videoyoutubelist.VideoYoutubeListModel;
import com.android.tupple.robot.utils.RxUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class

MediaModelImpl implements VideoListModel<Media>, AudioListModel<Media>, VideoYoutubeListModel<Media> {
    private Context mContext;
    private MediaDao mMediaDao;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public MediaModelImpl(Context mContext) {
        this.mContext = mContext;
        mMediaDao = KidRobotDatabase.getInstance(mContext).mediaDao();
    }


    @Override
    public CleanObservable<List<Media>> getAllVideo() {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mMediaDao
                            .getListMediabyType("video")
                            .compose(RxUtils.async())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace)
            );
        });
    }

    @Override
    public CleanObservable updateVideo(Media media) {
        return CleanObservable.create(cleanObserver -> {
            mMediaDao.update(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });
    }

    @Override
    public CleanObservable<List<Media>> getAllAudio() {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mMediaDao
                            .getListMediabyType("audio")
                            .compose(RxUtils.async())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace)
            );
        });
    }

    @Override
    public CleanObservable updateAudio(Media media) {
        return null;
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
