package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.Columns;
import com.android.tupple.robot.data.entity.LessonData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by tung.ts on 1/29/2020.
 */

@Dao
public abstract class LessonDao extends BaseDao<LessonData> {

    @Query("SELECT * FROM " + LessonData.TABLE_NAME)
    public abstract Observable<List<LessonData>> loadAllLesson();

    @Query("SELECT * FROM " + LessonData.TABLE_NAME + " WHERE " + Columns.LessonOfBook.BOOK_GRADLE  + " = :bookId")
    public abstract Observable<List<LessonData>> loadListLessonByBook(int bookId);

    @Query("SELECT " + Columns.LessonOfBook.BOOK_GRADLE + " FROM " + LessonData.TABLE_NAME + " WHERE " + Columns.LessonOfBook._ID + " = :lessonId")
    public abstract int getBookGradleFromLessonId(int lessonId);

    @Query("UPDATE " + LessonData.TABLE_NAME + " SET " + Columns.LessonOfBook.IS_LEARNING + " = :isLearning " + "WHERE " + Columns.LessonOfBook._ID + " = :lessonId")
    public abstract Single<Integer> updateLessonDataLearning(boolean isLearning, int lessonId);

}
