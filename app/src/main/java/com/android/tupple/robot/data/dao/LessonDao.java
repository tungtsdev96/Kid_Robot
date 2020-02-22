package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.LessonData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tung.ts on 1/29/2020.
 */

@Dao
public abstract class LessonDao extends BaseDao<LessonData> {

    @Query("SELECT * FROM " + LessonData.TABLE_NAME)
    public abstract Observable<List<LessonData>> loadAllLesson();

}
