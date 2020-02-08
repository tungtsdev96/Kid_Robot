package com.android.tupple.robot.domain.entity.testvocab;

import com.android.tupple.robot.domain.entity.IPresenter;
import com.android.tupple.robot.domain.presenter.PresenterObserver;

/**
 * Created by tungts on 2020-02-05.
 */

public interface TestVocabPresenter extends IPresenter {

    TestVocabLevel getLevel();

    void setOnNextLevelObserver(PresenterObserver<TestVocabLevel> onNextLevelObserver);

}
