package com.android.tupple.robot.view.testvocab.level3.item;

import android.content.Context;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemViewWrapper;

/**
 * Created by tungts on 2020-02-22.
 */

public class Level3ItemViewWrapperFactory {

    public static Level3ItemViewWrapper<Vocabulary> newLevel3ItemViewWrapper(Context context, int keyView) {
        return new Level3ItemViewWrapperImpl(context, keyView);
    }

}
