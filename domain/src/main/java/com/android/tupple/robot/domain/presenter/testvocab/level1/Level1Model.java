package com.android.tupple.robot.domain.presenter.testvocab.level1;

import com.android.tupple.robot.domain.presenter.IModel;

import java.util.List;

/**
 * Created by tungts on 2020-02-05.
 */

public interface Level1Model<LessonData, Topic, Vocabulary> extends IModel {

    int getAnswerRight(List<Vocabulary> listAnswers, Vocabulary question);

}
