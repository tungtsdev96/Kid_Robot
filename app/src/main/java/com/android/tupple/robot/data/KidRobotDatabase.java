package com.android.tupple.robot.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.tupple.robot.data.dao.LessonDao;
import com.android.tupple.robot.data.dao.MediaDao;
import com.android.tupple.robot.data.dao.SchoolBookDao;
import com.android.tupple.robot.data.dao.TopicDao;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;

/**
 * Created by tung.ts on 1/29/2020.
 * The database contains table:
 *   1, school_book, lesson_of_book, topic, vocabulary <done>
 *   2, event, alarm_clock
 */

@Database(entities = {SchoolBook.class, LessonData.class, Topic.class, Vocabulary.class, Media.class},
        version = 1, exportSchema = false)
public abstract class KidRobotDatabase extends RoomDatabase {

    public static final String TAG = "KidRobotDatabase";

//    private static final Migration MIGRATION_1_9 = new Migration(1, 9) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            upgradeDB(database, 1);
//        }
//    };

    private static KidRobotDatabase INSTANCE;

    public static KidRobotDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    KidRobotDatabase.class,
                    "kid_robot.db")
                    .addMigrations() // add MIGRATION_1_9 to here
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

    public abstract SchoolBookDao schoolBookDao();

    public abstract LessonDao lessonDao();

    public abstract TopicDao topicDao();

    public abstract VocabularyDao vocabularyDao();

    public abstract MediaDao mediaDao();
}
