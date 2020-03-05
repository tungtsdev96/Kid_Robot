package com.android.tupple.robot.data.model.english;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.TopicDao;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishTopicModelImpl implements EnglishTopicModel<Topic> {

    private final String TAG = "EnglishTopicModelImpl";

    private Context mContext;

    private TopicDao mTopicDao;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EnglishTopicModelImpl(Context mContext) {
        this.mContext = mContext;
        mTopicDao = KidRobotDatabase.getInstance(mContext).topicDao();
    }

    @Override
    public CleanObservable<List<Topic>> getAllTopic() {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mTopicDao.loadAllTopic()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace));
        });
    }

    @Override
    public void cancel() {
        mCompositeDisposable.clear();
    }

    @Override
    public void destroy() {
        mCompositeDisposable.dispose();
    }
}
