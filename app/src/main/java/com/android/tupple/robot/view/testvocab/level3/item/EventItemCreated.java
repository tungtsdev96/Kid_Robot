package com.android.tupple.robot.view.testvocab.level3.item;

import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemView;

/**
 * Created by tungts on 2020-02-22.
 */

public class EventItemCreated {

    public Level3ItemView<Vocabulary> level3ItemView;
    public int keyView;

    public EventItemCreated(Level3ItemView<Vocabulary> level3ItemView, int keyView) {
        this.level3ItemView = level3ItemView;
        this.keyView = keyView;
    }
}
