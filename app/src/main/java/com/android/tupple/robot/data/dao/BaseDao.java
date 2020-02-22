package com.android.tupple.robot.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by tung.ts on 2/17/2020
 */

@Dao
public abstract class BaseDao<T> {

    // where the value emitted on onSuccess is the row id of the item inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<List<Long>> insert(T... t);

    // where the value emitted on onSuccess is the list of row ids of the items inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<List<Long>> insert(List<T> t);

    // where onComplete is called as soon as the update/delete was done
    @Update
    public abstract Completable update(T... t);

    // where the value emitted on onSuccess is the number of rows affected by update/delete
    @Update
    public abstract Single<Integer> update(List<T> t);

    @Delete
    public abstract Completable delete(T... t);

    @Delete
    public abstract Single<Integer> delete(List<T> t);

    //    @Transaction
//    public List<String> getImageFileName(List<String> reminderUuidList) {
//        List<String> fileName = new ArrayList<>();
//        fileName.addAll(getCardDataImageNameList(reminderUuidList));
//        fileName.addAll(getAttachedFileNameList(reminderUuidList));
//        return fileName;
//    }

//    @Query("SELECT " + CardData.DATA_4 + " FROM " + CardData.TABLE_NAME + " WHERE " + CardData.DATA_4 + " IS NOT NULL AND " + CardData.REMINDER_UUID + " IN (:reminderUuids)")
//    public abstract List<String> getCardDataImageNameList(List<String> reminderUuids);

//    @Query("SELECT " + AttachedFile.ORIGINAL_IMAGE_PATH + " FROM " + AttachedFile.TABLE_NAME + " WHERE " + AttachedFile.REMINDER_UUID + " IN (:reminderUuids)")
//    public abstract List<String> getAttachedFileNameList(List<String> reminderUuids);

}
