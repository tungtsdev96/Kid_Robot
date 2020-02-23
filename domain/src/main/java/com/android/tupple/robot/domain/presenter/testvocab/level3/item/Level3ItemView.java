package com.android.tupple.robot.domain.presenter.testvocab.level3.item;

import com.android.tupple.cleanobject.CleanObservable;

/**
 * Created by tungts on 2020-02-22.
 */

public interface Level3ItemView<Vocabulary> {

    void setVocabulary(Vocabulary vocabulary);

    CleanObservable getBtnPronouneClickedObservable();

}
