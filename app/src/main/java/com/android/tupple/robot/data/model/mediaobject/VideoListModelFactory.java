package com.android.tupple.robot.data.model.mediaobject;

import android.content.Context;

import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.model.english.EnglishBookModelImpl;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;
import com.android.tupple.robot.domain.presenter.videolist.VideoListModel;

public class VideoListModelFactory {
    public static VideoListModel<Media> newVideoListModel(Context context) {
        return new MediaModelImpl(context);
    }

}
