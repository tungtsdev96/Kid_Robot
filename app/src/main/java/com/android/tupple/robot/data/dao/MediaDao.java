package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.Columns;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.Vocabulary;

import java.util.List;

import io.reactivex.Observable;

@Dao
public abstract class MediaDao extends BaseDao<Media> {
    @Query("SELECT * FROM " + Media.TABLE_NAME + " WHERE " + Columns.Media.MEDIA_TYPE + " = :type")
    public abstract Observable<List<Media>> getListMediabyType(String type);

//    @Query("SELECT * FROM " + Media.TABLE_NAME + " WHERE " + Columns.Media.MEDIA_TYPE + " = :type")
//    public abstract Observable<List<Vocabulary>> getListMediabyType(String type);

}
