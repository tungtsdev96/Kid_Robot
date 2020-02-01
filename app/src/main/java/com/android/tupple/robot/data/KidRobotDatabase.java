package com.android.tupple.robot.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.SchoolBook;

/**
 * Created by tung.ts on 1/29/2020.
 * The database contains table:
 *   1, school_book, lesson_of_book, topic, vocabulary
 *   2, event, alarm_clock
 */

@Database(entities = {SchoolBook.class, LessonData.class},
        version = 1, exportSchema = false)
public abstract class KidRobotDatabase extends RoomDatabase {

    public static final String TAG = "KidRobotDatabase";

    private static KidRobotDatabase INSTANCE;

    public static KidRobotDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    KidRobotDatabase.class,
                    "kid_robot.db")
                    .addMigrations()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Log.d(TAG,"onCreate");
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            Log.d(TAG,"onOpen");
                        }

                        @Override
                        public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                            super.onDestructiveMigration(db);
                            Log.d(TAG,"onDestructiveMigration");
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

}
