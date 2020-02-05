package com.android.tupple.robot.data.model.testvocab.level1;

import android.content.Context;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1Model;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-02-05.
 * TODO random, convert data
 *      Just handle data for level1presenter
 */

public class Level1ModelImpl implements Level1Model<LessonData, Topic, Vocabulary> {

    private Context mContext;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public Level1ModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }
}
