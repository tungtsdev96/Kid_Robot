package com.android.tupple.robot.data.model.english;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.R;
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

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishTopicModelImpl implements EnglishTopicModel<Topic> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EnglishTopicModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public CleanObservable<List<Topic>> getAllTopic() {
        return CleanObservable.create(cleanObserver -> {
            try {
                cleanObserver.onNext(readJsonStream(mContext.getResources().openRawResource(R.raw.topic)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                cleanObserver.onNext(new ArrayList<>());
            }
        });
    }

    public ArrayList<Topic> readJsonStream(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Topic>>(){}.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
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
