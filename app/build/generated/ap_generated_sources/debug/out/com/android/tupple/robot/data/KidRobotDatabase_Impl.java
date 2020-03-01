package com.android.tupple.robot.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.android.tupple.robot.data.dao.LessonDao;
import com.android.tupple.robot.data.dao.LessonDao_Impl;
import com.android.tupple.robot.data.dao.SchoolBookDao;
import com.android.tupple.robot.data.dao.SchoolBookDao_Impl;
import com.android.tupple.robot.data.dao.TopicDao;
import com.android.tupple.robot.data.dao.TopicDao_Impl;
import com.android.tupple.robot.data.dao.VocabularyDao;
import com.android.tupple.robot.data.dao.VocabularyDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class KidRobotDatabase_Impl extends KidRobotDatabase {
  private volatile SchoolBookDao _schoolBookDao;

  private volatile LessonDao _lessonDao;

  private volatile TopicDao _topicDao;

  private volatile VocabularyDao _vocabularyDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `school_book` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `book_gradle` INTEGER NOT NULL, `total_lesson` INTEGER NOT NULL, `book_title` TEXT, `is_learning` INTEGER NOT NULL DEFAULT 0, `image` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `lesson_of_book` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `book_gradle` INTEGER NOT NULL, `lesson_title` TEXT, `lesson_priority` INTEGER NOT NULL, `is_learning` INTEGER NOT NULL DEFAULT 0, `progress_learning` INTEGER NOT NULL DEFAULT 0, `total_vocab` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `topic` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `lesson_title` TEXT, `is_learning` INTEGER NOT NULL DEFAULT 0, `progress_learning` INTEGER NOT NULL DEFAULT 0, `total_vocab` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `vocabulary` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `vocab_en` TEXT, `vocab_vi` TEXT, `image_url` TEXT, `total_image` INTEGER NOT NULL DEFAULT 1, `audio_url` TEXT, `score_correct` INTEGER NOT NULL DEFAULT 0, `score_wrong` INTEGER NOT NULL DEFAULT 0, `topic_id` INTEGER NOT NULL DEFAULT -1, `lesson_id` INTEGER NOT NULL DEFAULT -1)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '36fc143bb601ff8598795c837be58178')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `school_book`");
        _db.execSQL("DROP TABLE IF EXISTS `lesson_of_book`");
        _db.execSQL("DROP TABLE IF EXISTS `topic`");
        _db.execSQL("DROP TABLE IF EXISTS `vocabulary`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSchoolBook = new HashMap<String, TableInfo.Column>(6);
        _columnsSchoolBook.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchoolBook.put("book_gradle", new TableInfo.Column("book_gradle", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchoolBook.put("total_lesson", new TableInfo.Column("total_lesson", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchoolBook.put("book_title", new TableInfo.Column("book_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchoolBook.put("is_learning", new TableInfo.Column("is_learning", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsSchoolBook.put("image", new TableInfo.Column("image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSchoolBook = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSchoolBook = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSchoolBook = new TableInfo("school_book", _columnsSchoolBook, _foreignKeysSchoolBook, _indicesSchoolBook);
        final TableInfo _existingSchoolBook = TableInfo.read(_db, "school_book");
        if (! _infoSchoolBook.equals(_existingSchoolBook)) {
          return new RoomOpenHelper.ValidationResult(false, "school_book(com.android.tupple.robot.data.entity.SchoolBook).\n"
                  + " Expected:\n" + _infoSchoolBook + "\n"
                  + " Found:\n" + _existingSchoolBook);
        }
        final HashMap<String, TableInfo.Column> _columnsLessonOfBook = new HashMap<String, TableInfo.Column>(7);
        _columnsLessonOfBook.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("book_gradle", new TableInfo.Column("book_gradle", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("lesson_title", new TableInfo.Column("lesson_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("lesson_priority", new TableInfo.Column("lesson_priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("is_learning", new TableInfo.Column("is_learning", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("progress_learning", new TableInfo.Column("progress_learning", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonOfBook.put("total_vocab", new TableInfo.Column("total_vocab", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLessonOfBook = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLessonOfBook = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLessonOfBook = new TableInfo("lesson_of_book", _columnsLessonOfBook, _foreignKeysLessonOfBook, _indicesLessonOfBook);
        final TableInfo _existingLessonOfBook = TableInfo.read(_db, "lesson_of_book");
        if (! _infoLessonOfBook.equals(_existingLessonOfBook)) {
          return new RoomOpenHelper.ValidationResult(false, "lesson_of_book(com.android.tupple.robot.data.entity.LessonData).\n"
                  + " Expected:\n" + _infoLessonOfBook + "\n"
                  + " Found:\n" + _existingLessonOfBook);
        }
        final HashMap<String, TableInfo.Column> _columnsTopic = new HashMap<String, TableInfo.Column>(5);
        _columnsTopic.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTopic.put("lesson_title", new TableInfo.Column("lesson_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTopic.put("is_learning", new TableInfo.Column("is_learning", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsTopic.put("progress_learning", new TableInfo.Column("progress_learning", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsTopic.put("total_vocab", new TableInfo.Column("total_vocab", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTopic = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTopic = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTopic = new TableInfo("topic", _columnsTopic, _foreignKeysTopic, _indicesTopic);
        final TableInfo _existingTopic = TableInfo.read(_db, "topic");
        if (! _infoTopic.equals(_existingTopic)) {
          return new RoomOpenHelper.ValidationResult(false, "topic(com.android.tupple.robot.data.entity.Topic).\n"
                  + " Expected:\n" + _infoTopic + "\n"
                  + " Found:\n" + _existingTopic);
        }
        final HashMap<String, TableInfo.Column> _columnsVocabulary = new HashMap<String, TableInfo.Column>(10);
        _columnsVocabulary.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("vocab_en", new TableInfo.Column("vocab_en", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("vocab_vi", new TableInfo.Column("vocab_vi", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("total_image", new TableInfo.Column("total_image", "INTEGER", true, 0, "1", TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("audio_url", new TableInfo.Column("audio_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("score_correct", new TableInfo.Column("score_correct", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("score_wrong", new TableInfo.Column("score_wrong", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("topic_id", new TableInfo.Column("topic_id", "INTEGER", true, 0, "-1", TableInfo.CREATED_FROM_ENTITY));
        _columnsVocabulary.put("lesson_id", new TableInfo.Column("lesson_id", "INTEGER", true, 0, "-1", TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVocabulary = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVocabulary = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVocabulary = new TableInfo("vocabulary", _columnsVocabulary, _foreignKeysVocabulary, _indicesVocabulary);
        final TableInfo _existingVocabulary = TableInfo.read(_db, "vocabulary");
        if (! _infoVocabulary.equals(_existingVocabulary)) {
          return new RoomOpenHelper.ValidationResult(false, "vocabulary(com.android.tupple.robot.data.entity.Vocabulary).\n"
                  + " Expected:\n" + _infoVocabulary + "\n"
                  + " Found:\n" + _existingVocabulary);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "36fc143bb601ff8598795c837be58178", "839012ae45f5b0f42db6796d159f34f9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "school_book","lesson_of_book","topic","vocabulary");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `school_book`");
      _db.execSQL("DELETE FROM `lesson_of_book`");
      _db.execSQL("DELETE FROM `topic`");
      _db.execSQL("DELETE FROM `vocabulary`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public SchoolBookDao schoolBookDao() {
    if (_schoolBookDao != null) {
      return _schoolBookDao;
    } else {
      synchronized(this) {
        if(_schoolBookDao == null) {
          _schoolBookDao = new SchoolBookDao_Impl(this);
        }
        return _schoolBookDao;
      }
    }
  }

  @Override
  public LessonDao lessonDao() {
    if (_lessonDao != null) {
      return _lessonDao;
    } else {
      synchronized(this) {
        if(_lessonDao == null) {
          _lessonDao = new LessonDao_Impl(this);
        }
        return _lessonDao;
      }
    }
  }

  @Override
  public TopicDao topicDao() {
    if (_topicDao != null) {
      return _topicDao;
    } else {
      synchronized(this) {
        if(_topicDao == null) {
          _topicDao = new TopicDao_Impl(this);
        }
        return _topicDao;
      }
    }
  }

  @Override
  public VocabularyDao vocabularyDao() {
    if (_vocabularyDao != null) {
      return _vocabularyDao;
    } else {
      synchronized(this) {
        if(_vocabularyDao == null) {
          _vocabularyDao = new VocabularyDao_Impl(this);
        }
        return _vocabularyDao;
      }
    }
  }
}
