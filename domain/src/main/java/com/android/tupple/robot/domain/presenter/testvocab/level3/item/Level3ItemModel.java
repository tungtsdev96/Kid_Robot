package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import android.util.SparseBooleanArray;

import com.android.tupple.cleanobject.CleanObservable;
import com.android.tupple.robot.domain.presenter.IModel;

/**
 * Created by tungts on 3/25/20.
 */

public interface Level3ItemModel<Vocabulary> extends IModel {

    void sendFileRecordToServer(String fileRecord);

    CleanObservable<boolean[]> getAnswerFromUserObservable(String result, Vocabulary rightAnswer);

}
