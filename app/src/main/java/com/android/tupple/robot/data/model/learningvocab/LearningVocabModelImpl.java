package com.android.tupple.robot.data.model.learningvocab;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.learnvocab.LearningVocabModel;
import com.android.tupple.robot.utils.RxUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tungts on 2020-02-16.
 */

public class LearningVocabModelImpl implements LearningVocabModel<Vocabulary> {

    private WeakReference<Context> mContext;
    private VocabularyDao mVocabularyDao;

    private List<Vocabulary> listVocabLearning = new ArrayList<>();

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private static LearningVocabModelImpl INSTANCE;

    private LearningVocabModelImpl() {
    }

    public static LearningVocabModelImpl newInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LearningVocabModelImpl();
        }
        return INSTANCE.setContext(context);
    }

    private LearningVocabModelImpl setContext(Context context) {
        mContext = new WeakReference<>(context);
        mVocabularyDao = KidRobotDatabase.getInstance(mContext.get()).vocabularyDao();
        return INSTANCE;
    }

    @Override
    public CleanObservable<List<Vocabulary>> makeListVocabLearningByTopicId(int topicId) {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    mVocabularyDao
                            .makeListVocabularyLearningFromTopic(topicId)
                            .compose(RxUtils.async())
                            .subscribe(vocabularies -> {
                                listVocabLearning.clear();
                                listVocabLearning.addAll(vocabularies);
                                cleanObserver.onNext(vocabularies);
                            }, Throwable::printStackTrace)
            );
        });
    }

    @Override
    public CleanObservable<List<Vocabulary>> getListVocabLearningByLessonId(int lessonId) {
        return CleanObservable.create(cleanObserver -> {
            Disposable disposable =
                    mVocabularyDao.makeListVocabularyLearningFromLesson(lessonId)
                            .compose(RxUtils.async())
                            .subscribe(vocabularies -> {
                                listVocabLearning.clear();
                                listVocabLearning.addAll(vocabularies);
                                cleanObserver.onNext(vocabularies);
                            }, Throwable::printStackTrace);

            mCompositeDisposable.add(disposable);
        });
    }

    @Override
    public List<Vocabulary> getListVocabLearning() {
        return listVocabLearning;
    }


    @Override
    public void cancel() {
        mCompositeDisposable.clear();
        mContext.clear();
    }

    @Override
    public void destroy() {
        mContext.clear();
        mContext = null;
        mCompositeDisposable.dispose();
        listVocabLearning.clear();
    }
}
