package com.android.tupple.robot.data.model.testvocab.level3;

import android.content.Context;
import android.util.SparseBooleanArray;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemModel;
import com.android.tupple.robot.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 3/25/20.
 */

public class Level3ItemModelImpl implements Level3ItemModel<Vocabulary> {

    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public Level3ItemModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void sendFileRecordToServer(String fileRecord) {

    }

    @Override
    public CleanObservable<boolean[]> getAnswerFromUserObservable(String result, Vocabulary vocabulary) {
        return CleanObservable.create(cleanObserver -> {
            mCompositeDisposable.add(
                    Observable.fromCallable(() -> {
                        boolean[] listCharacter = new boolean[result.length()];
                        String rightAnswer = vocabulary.getVocabEn();
                        int lengthResult = result.length();
                        int lengthRightAnswer = rightAnswer.length();

                        if (lengthResult < lengthRightAnswer || lengthResult > lengthRightAnswer) {
                            for (int i = 0; i < lengthResult; i++) {
                                listCharacter[i] = true;
                            }
                        } else {
                            for (int i = 0; i < lengthResult; i++) {
                                listCharacter[i] = result.charAt(i) == rightAnswer.charAt(i);
                            }
                        }
                        return listCharacter;
                    }).compose(RxUtils.async()).subscribe(cleanObserver::onNext)
            );
        });
    }

    @Override
    public void cancel() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void destroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
