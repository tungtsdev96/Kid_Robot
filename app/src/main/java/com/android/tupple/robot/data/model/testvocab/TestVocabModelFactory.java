package com.android.tupple.robot.data.model.testvocab;

import android.content.Context;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.data.model.testvocab.level1.Level1ModelImpl;
import com.android.tupple.robot.data.model.testvocab.level2.Level2ModelImpl;
import com.android.tupple.robot.data.model.testvocab.level3.Level3ItemModelImpl;
import com.android.tupple.robot.domain.presenter.testvocab.TestVocabModel;
import com.android.tupple.robot.domain.presenter.testvocab.level1.Level1Model;
import com.android.tupple.robot.domain.presenter.testvocab.level2.Level2Model;
import com.android.tupple.robot.domain.presenter.testvocab.level3.item.Level3ItemModel;

/**
 * Created by tungts on 2020-02-05.
 */

public class TestVocabModelFactory {

    public static TestVocabModel<LessonData, Topic, Vocabulary> newTestVocabModel(Context context) {
        return new TestVocabModelImpl(context);
    }

    public static Level1Model<LessonData, Topic, Vocabulary> newLevel1Model(Context context) {
        return new Level1ModelImpl(context);
    }

    public static Level2Model<LessonData, Topic, Vocabulary> newLevel2Model(Context context) {
        return new Level2ModelImpl(context);
    }

    public static Level3ItemModel<Vocabulary> newLevel3ItemModel(Context context) {
        return new Level3ItemModelImpl(context);
    }
}
