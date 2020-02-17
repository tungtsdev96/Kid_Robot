package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.Columns;
import com.android.tupple.robot.data.entity.Vocabulary;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tung.ts on {2/17/2020}
 */

@Dao
public abstract class VocabularyDao extends BaseDao<Vocabulary> {

    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.TOPIC_ID + " = :topicId")
    public abstract Observable<List<Vocabulary>> getListVocabularyByTopicId(int topicId);

    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.LESSON_ID + " = :lessonId")
    public abstract Observable<List<Vocabulary>> getListVocabularyByLessonId(int lessonId);

}
