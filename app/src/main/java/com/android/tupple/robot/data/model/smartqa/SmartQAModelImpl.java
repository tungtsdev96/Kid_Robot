package com.android.tupple.robot.data.model.smartqa;

import android.content.Context;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.data.remote.ApiFactory;
import com.android.tupple.robot.data.remote.SmartQAService;
import com.android.tupple.robot.data.remote.questionanswer.QARequest;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAModelImpl implements SmartQAModel<QAResponse> {

    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private SmartQAService mSmartQAService;

    public SmartQAModelImpl(Context context) {
        this.mContext = context;
        mSmartQAService = ApiFactory.getQAService();
    }

    @Override
    public CleanObservable<QAResponse> getAnswer(String question) {
        return CleanObservable.create(cleanObserver -> {
            Disposable d = mSmartQAService
                    .postTest(new QARequest(question))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(cleanObserver::onNext, cleanObserver::onError);

            mCompositeDisposable.add(d);
        });
    }

    @Override
    public void stop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void destroy() {
        mCompositeDisposable.dispose();
    }
}