package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.SchoolBook;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tung.ts on {2/17/2020}
 */

@Dao
public abstract class SchoolBookDao extends BaseDao<SchoolBook> {

    @Query("SELECT * FROM " + SchoolBook.TABLE_NAME)
    public abstract Observable<List<SchoolBook>> loadAllBook();

}
