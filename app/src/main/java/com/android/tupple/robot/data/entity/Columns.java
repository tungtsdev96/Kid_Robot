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

}
