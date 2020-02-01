package com.android.tupple.robot.data.model.vocabulary;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.data.VocabularyModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class VocabularyModelImpl implements VocabularyModel<Vocabulary> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public VocabularyModelImpl(Context context) {
        this.mContext = context;
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
