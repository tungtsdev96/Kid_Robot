package com.android.tupple.robot.data.model.learningvocab;

import android.content.Context;
import android.util.Log;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-02-16.
 */

public class LearningVocabModelImpl implements LearningVocabModel<Vocabulary> {

    private WeakReference<Context> mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public LearningVocabModelImpl(Context context) {
        this.mContext = new WeakReference<>(context);
        Log.d("LearningVocabModelImpl", mContext.hashCode() + "");
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }

}
