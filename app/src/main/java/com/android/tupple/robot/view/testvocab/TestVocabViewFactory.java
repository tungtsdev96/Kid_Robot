package com.android.tupple.robot.view.testvocab;

import androidx.fragment.app.FragmentManager;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1ViewWrapper;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2ViewWrapper;
import com.android.tupple.robot.view.testvocab.level1.Level1ViewWrapperImpl;
import com.android.tupple.robot.view.testvocab.level2.Level2ViewWrapperImpl;

/**
 * Created by tungts on 2020-02-05.
 */

public class TestVocabViewFactory {

    public static Level1ViewWrapper<LessonData, Topic, Vocabulary> newLevel1ViewWrapper(FragmentManager fragmentManager) {
        return new Level1ViewWrapperImpl(fragmentManager);
    }

    public static Level2ViewWrapper<LessonData, Topic, Vocabulary> newLevel2ViewWrapper(FragmentManager fragmentManager) {
        return new Level2ViewWrapperImpl(fragmentManager);
    }
}
