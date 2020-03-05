package com.android.tupple.robot.view.testvocab.level3.item;

import android.content.Context;
import android.util.Log;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.cleanobject.CleanObserver;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemViewWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemViewWrapperImpl implements Level3ItemViewWrapper<Vocabulary> {

    public static final String TAG = "L3ItemViewWrapperImpl";

    public Context mContext;

    private int mKeyView;

    CleanObserver<Level3ItemView<Vocabulary>> mOnViewCreated;

    Level3ItemViewWrapperImpl(Context context, int keyView) {
        this.mContext = context;
        this.mKeyView = keyView;

        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
        }

    }

    @Override
    public CleanObservable<Level3ItemView<Vocabulary>> getViewCreatedObservable() {
        return CleanObservable.create(cleanObserver -> mOnViewCreated = cleanObserver);
    }

    @Subscribe
    public void onViewCreated(EventItemCreated itemCreated) {
        if (mKeyView != itemCreated.keyView) {
            return;
        }

        Log.d(TAG, "tungts onViewCreated " + itemCreated.keyView + " " + itemCreated.level3ItemView);
        if (mOnViewCreated != null) {
            mOnViewCreated.onNext(itemCreated.level3ItemView);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        try {
            EventBus.getDefault().unregister(this);
        } catch (Exception e) {
        }
    }

    @Override
    public void invalidate() {

    }
}
