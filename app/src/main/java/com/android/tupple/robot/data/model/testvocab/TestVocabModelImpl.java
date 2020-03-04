package com.android.tupple.robot.data.model.testvocab;

import android.content.Context;
import android.util.Log;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.dao.LessonDao;
import com.android.tupple.robot.data.dao.TopicDao;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.TestVocabModel;
import com.android.tupple.robot.utils.RxUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by tungts on 2020-02-05.
 */

public class TestVocabModelImpl implements TestVocabModel<LessonData, Topic, Vocabulary> {

    private Context mContext;
    private LessonDao mLessonDao;
    private TopicDao mTopicDao;
    private VocabularyDao mVocabularyDao;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public TestVocabModelImpl(Context context) {
        this.mContext = context;
        mLessonDao = KidRobotDatabase.getInstance(context).lessonDao();
        mTopicDao = KidRobotDatabase.getInstance(context).topicDao();
        mVocabularyDao = KidRobotDatabase.getInstance(context).vocabularyDao();
    }

    @Override
    public CleanObservable<List<Vocabulary>> transformListVocabLearning(List<Vocabulary> vocabs) {
        return CleanObservable.create(cleanObserver -> {
            cleanObserver.onNext(pickRandom(vocabs, vocabs.size()));
        });
    }

    //random n element in list
    private List<Vocabulary> pickRandom(List<Vocabulary> list, int n) {
        List<Vocabulary> copy = new LinkedList<>(list);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }

    @Override
    public CleanObservable<List<Vocabulary>> makeListAnswerFromVocab(List<Vocabulary> vocabLearning, Vocabulary vocabulary) {
        return CleanObservable.create(cleanObserver -> {
            if (vocabulary.getLessonId() != 0) {
                // TODO get answer from lesson
            } else {
                Disposable d = makeListAnswerFromTopic(vocabulary)
                        .compose(RxUtils.async())
                        .subscribe(cleanObserver::onNext, Throwable::printStackTrace);
                mCompositeDisposable.add(d);
            }
        });
    }

    private Observable<List<Vocabulary>> makeListAnswerFromLesson(Vocabulary vocabulary) {
        List<Integer> ids = new ArrayList<>();
        int bookGradle = mLessonDao.getBookGradleFromLessonId(vocabulary.getLessonId());


        return null;
    }

    // Random in the same topic -> easy => Need to be more difficult
    private Observable<List<Vocabulary>> makeListAnswerFromTopic(Vocabulary vocabulary) {
        List<Integer> ids = new ArrayList<>();
        ids.add(vocabulary.getVocabId());

        return mVocabularyDao
                        .getListVocabularyNotIncludeIds(ids, vocabulary.getTopicId())
                        .map(listVocabs -> pickRandom(listVocabs, 4))
                        .map(listVocabs -> {
                            List<Vocabulary> answers = new ArrayList<>();
                            answers.add(vocabulary);
                            answers.add(listVocabs.get(0));
                            answers.add(listVocabs.get(1));
                            answers.add(listVocabs.get(2));
                            return pickRandom(answers, answers.size());
                        });
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }

}
