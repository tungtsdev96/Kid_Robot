package com.android.tupple.robot.data.model.testvocab.level2;

import android.content.Context;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2Model;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by tungts on 2020-02-05.
 * TODO random, convert data
 *      Just handle data for level2presenter
 */

public class Level2ModelImpl implements Level2Model<LessonData, Topic, Vocabulary> {

    private Context mContext;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public Level2ModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void cancel() {

    }

    @Override
    public void destroy() {

    }
}
