package com.android.tupple.robot.model.english;

import android.content.Context;

import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishModelFactory {

    public static EnglishBookModel<SchoolBook> newEnglishBookModel(Context context) {
        return new EnglishBookModelImpl(context);
    }

    public static EnglishTopicModel<Topic> newEnglishTopicModel(Context context) {
        return new EnglishTopicModelImpl(context);
    }

}
