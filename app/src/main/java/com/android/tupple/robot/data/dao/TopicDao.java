package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.android.tupple.robot.data.entity.Topic;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tung.ts on {2/17/2020}
 */

@Dao
public abstract class TopicDao extends BaseDao<Topic> {

    @Query("SELECT * FROM " + Topic.TABLE_NAME)
    public abstract Observable<List<Topic>> loadAllTopic();

}
