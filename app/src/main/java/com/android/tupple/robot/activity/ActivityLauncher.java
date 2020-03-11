package com.android.tupple.robot.activity;

import android.app.Activity;
import android.content.Intent;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.utils.ActivityUtils;
import com.android.tupple.robot.utils.constant.BookConstant;
import com.android.tupple.robot.utils.constant.EntertainmentConstant;
import com.android.tupple.robot.utils.constant.LearnVocabConstant;
import com.android.tupple.robot.utils.constant.LessonConstant;
import com.android.tupple.robot.utils.constant.TopicConstant;

/**
 * Created by tungts on 2020-01-12.
 */

public class ActivityLauncher {

    private Activity mActivity;

    public ActivityLauncher(Activity activity) {
        mActivity = activity;
    }

    public void launchLessonActivity(SchoolBook book) {
        Intent intent = new Intent(mActivity, LessonActivity.class);
        intent.putExtra(BookConstant.EXTRA_BOOK, book);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchLearningVocabActivity(LessonData lessonData) {
        Intent intent = new Intent(mActivity, LearningVocabActivity.class);
        intent.putExtra(LessonConstant.EXTRA_LESSON, lessonData);
        intent.putExtra(LearnVocabConstant.TestVocab.EXTRA_IS_LESSON, true);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchLearningVocabActivity(Topic topic) {
        Intent intent = new Intent(mActivity, LearningVocabActivity.class);
        intent.putExtra(TopicConstant.EXTRA_TOPIC, topic);
        intent.putExtra(LearnVocabConstant.TestVocab.EXTRA_IS_TOPIC, true);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchTestVocabActivity() {
        Intent intent = new Intent(mActivity, TestVocabActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchVideoPlayerActivity(Media media) {
        Intent intent = new Intent(mActivity, VideoPlayerActivity.class);
        intent.putExtra(EntertainmentConstant.VIDEO_INTENT, media.getMedia_url());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityUtils.startActivty(mActivity, intent);
    }

    public void launchAudioPlayerActivity(Media media) {
        Intent intent = new Intent(mActivity, AudioPlayerActivity.class);
        intent.putExtra(EntertainmentConstant.AUDIO_INTENT, media).putExtra(EntertainmentConstant.AUDIO_POSITION, media.getId() - 1);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        ActivityUtils.startActivty(mActivity, intent);
    }
}
