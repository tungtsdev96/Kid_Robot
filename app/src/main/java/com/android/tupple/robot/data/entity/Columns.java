package com.android.tupple.robot.data.entity;

import android.provider.BaseColumns;

/**
 * Created by tung.ts on 1/29/2020.
 */

public class Columns {

    public interface SchoolBook {
        String _ID = BaseColumns._ID;
        String BOOK_GRADLE = "book_gradle";
        String TOTAL_LESSON = "total_lesson";
        String BOOK_TITLE = "book_title";
        String IS_LEARNING = "is_learning";
        String IMAGE = "image";
    }

    public interface LessonOfBook {
        String _ID = BaseColumns._ID;
        String BOOK_GRADLE = "book_gradle";
        String LESSON_TITLE = "lesson_title";
        String LESSON_PRIORITY = "lesson_priority";
        String IS_LEARNING = "is_learning";
        String PROGRESS_LEARNING = "progress_learning";
        String TOTAL_VOCAB = "total_vocab";
    }

    public interface Topic {
        String _ID = BaseColumns._ID;
        String TOPIC_TITLE = "lesson_title";
        String IS_LEARNING = "is_learning";
        String PROGRESS_LEARNING = "progress_learning";
        String TOTAL_VOCAB = "total_vocab";
    }

    public interface Vocabulary {
        String _ID = BaseColumns._ID;
        String VOCAB_EN = "vocab_en";
        String VOCAB_VI = "vocab_vi";
        String IMAGE_URL = "image_url";
        String AUDIO_URL = "audio_url";
        String TOTAL_IMAGE = "total_image";
        String SCORE_CORRECT = "score_correct";
        String SCORE_WRONG = "score_wrong";
        String TOPIC_ID = "topic_id";
        String LESSON_ID = "lesson_id";
    }

}
