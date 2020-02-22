package com.android.tupple.robot.data.model.vocabulary;

import android.content.Context;
import android.util.Log;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.R;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;
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
            cleanObserver.onNext(Vocabulary.fake());
        });
    }

    @Override
    public CleanObservable<List<Vocabulary>> getListVocabularyByTopicId(int topicId) {
        return CleanObservable.create(cleanObserver -> {
//            try {
//                ArrayList<Vocabulary> vocabularies = readJsonStream(mContext.getResources().openRawResource(R.raw.animals_t));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.alphabet_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.appliances_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.body_parts_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.clothing_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.colors_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.fruits_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.garden_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.job_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.kitchen_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.musical_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.numbers_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.school_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.shapes_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.sports_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.tools_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.transportation_t)));
//                vocabularies.addAll(readJsonStream(mContext.getResources().openRawResource(R.raw.vegetables_t)));
//                mCompositeDisposable.add(
//                        mVocabularyDao.insert(vocabularies).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(longs -> {
//                            cleanObserver.onNext(vocabularies);
//                        })
//                );
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            cleanObserver.onNext(Vocabulary.fake());
        });
    }

    public ArrayList<Vocabulary> readJsonStream(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Vocabulary>>() {
        }.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }
}
