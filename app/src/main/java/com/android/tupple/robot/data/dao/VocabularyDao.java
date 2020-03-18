package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.Columns;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Vocabulary;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by tung.ts on 2/17/2020
 */

@Dao
public abstract class VocabularyDao extends BaseDao<Vocabulary> {

    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.TOPIC_ID + " = :topicId")
    public abstract Observable<List<Vocabulary>> getListVocabularyByTopicId(int topicId);

    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.LESSON_ID + " = :lessonId")
    public abstract Observable<List<Vocabulary>> getListVocabularyByLessonId(int lessonId);

    // LEARNING VOCAB //
    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.TOPIC_ID + " = :topicId" +
            " AND (" + Columns.Vocabulary.SCORE_CORRECT + " - " + Columns.Vocabulary.SCORE_WRONG + ") < 3 " +
            " ORDER BY " + Columns.Vocabulary.SCORE_WRONG + " ASC LIMIT 4")
    public abstract Observable<List<Vocabulary>> makeListVocabularyLearningFromTopic(int topicId);

    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.LESSON_ID + " = :lessonId")
    public abstract Observable<List<Vocabulary>> makeListVocabularyLearningFromLesson(int lessonId);

    //  TEST VOCAB //
    @Query("SELECT * FROM " + Vocabulary.TABLE_NAME + " WHERE " + Columns.Vocabulary.TOPIC_ID + " = :topicId AND " + Columns.Vocabulary._ID + " NOT IN (:ids)" +
            " ORDER BY " + Columns.Vocabulary.SCORE_WRONG + " DESC LIMIT 3")
    public abstract Single<List<Vocabulary>> makeListAnswerFromTopic(List<Integer> ids, int topicId);

    @Query("SELECT " + Vocabulary.TABLE_NAME + ".*" + " FROM " + LessonData.TABLE_NAME + ", " + Vocabulary.TABLE_NAME
            + " WHERE " + Columns.LessonOfBook.BOOK_GRADLE + " = :bookGradle AND " + LessonData.TABLE_NAME + "." + Columns.LessonOfBook._ID + " = " + Vocabulary.TABLE_NAME + "." + Columns.Vocabulary.LESSON_ID
            + " AND " + Vocabulary.TABLE_NAME + "." + Columns.Vocabulary._ID + " NOT IN (:vocabId)" + " ORDER BY " + Columns.Vocabulary.SCORE_WRONG + " DESC LIMIT 3")
    abstract Single<List<Vocabulary>> makeListAnswerFromVocab(int vocabId, int bookGradle);

    public Single<List<Vocabulary>> makeListAnswerFromVocab(Vocabulary vocabulary, int bookGradle) {
        return makeListAnswerFromVocab(vocabulary.getVocabId(), bookGradle);
    }

}
