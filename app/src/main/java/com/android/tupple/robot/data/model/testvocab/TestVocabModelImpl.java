package com.android.tupple.robot.data.model.testvocab;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.data.TestVocabModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-02-05.
 */

public class TestVocabModelImpl implements TestVocabModel<LessonData, Topic, Vocabulary> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public TestVocabModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public CleanObservable<List<Vocabulary>> getAllVocabLearning() {
        return CleanObservable.create(cleanObserver -> {
            cleanObserver.onNext(Vocabulary.fake());
        });
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }

}
