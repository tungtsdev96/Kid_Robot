package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Video;
import com.android.tupple.robot.data.model.english.EnglishBookModelImpl;
import com.android.tupple.robot.data.model.english.EnglishTopicModelImpl;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.englishtopic.EnglishTopicModel;
import com.android.tupple.robot.domain.presenter.entertainment.EntertainmentModel;

public class EntertainmentModelFactory {

    public static EntertainmentModel<Video> newEntertainmentModel(Context context) {
        return new VideoModelImpl(context);
    }
}
