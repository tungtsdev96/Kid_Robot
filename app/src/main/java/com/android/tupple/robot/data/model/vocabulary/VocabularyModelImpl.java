package com.android.tupple.robot.data.model.vocabulary;

import android.content.Context;
import android.util.Log;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;
import com.android.tupple.robot.utils.RxUtils;
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
 * Created by tung.ts on 1/29/2020.
 */

public class VocabularyModelImpl implements VocabularyModel<Vocabulary> {

    private Context mContext;
    private VocabularyDao mVocabularyDao;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public VocabularyModelImpl(Context context) {
        this.mContext = context;
        mVocabularyDao = KidRobotDatabase.getInstance(context).vocabularyDao();
    }

    @Override
    public CleanObservable<Vocabulary> getVocabularybyId(int vocabId) {
        return null;
    }

    @Override
    public CleanObservable<List<Vocabulary>> getListVocabularyByLessonId(int lessonId) {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mVocabularyDao
                            .getListVocabularyByLessonId(lessonId)
                            .compose(RxUtils.async())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace)
            );        });
    }

    @Override
    public CleanObservable<List<Vocabulary>> getListVocabularyByTopicId(int topicId) {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mVocabularyDao
                            .getListVocabularyByTopicId(topicId)
                            .compose(RxUtils.async())
                            .subscribe(cleanObserver::onNext, Throwable::printStackTrace)
            );
        });
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }
}
