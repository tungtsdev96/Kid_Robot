package com.android.tupple.robot.data.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.android.tupple.robot.data.entity.LessonData;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.Void;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class LessonDao_Impl extends LessonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LessonData> __insertionAdapterOfLessonData;

  private final EntityDeletionOrUpdateAdapter<LessonData> __deletionAdapterOfLessonData;

  private final EntityDeletionOrUpdateAdapter<LessonData> __updateAdapterOfLessonData;

  public LessonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLessonData = new EntityInsertionAdapter<LessonData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `lesson_of_book` (`_id`,`book_gradle`,`lesson_title`,`lesson_priority`,`is_learning`,`progress_learning`,`total_vocab`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonData value) {
        stmt.bindLong(1, value.getLessonId());
        stmt.bindLong(2, value.getBookGradle());
        if (value.getLessonTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLessonTitle());
        }
        stmt.bindLong(4, value.getLessonPriority());
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getProgressLearning());
        stmt.bindLong(7, value.getTotalVocab());
      }
    };
    this.__deletionAdapterOfLessonData = new EntityDeletionOrUpdateAdapter<LessonData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `lesson_of_book` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonData value) {
        stmt.bindLong(1, value.getLessonId());
      }
    };
    this.__updateAdapterOfLessonData = new EntityDeletionOrUpdateAdapter<LessonData>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `lesson_of_book` SET `_id` = ?,`book_gradle` = ?,`lesson_title` = ?,`lesson_priority` = ?,`is_learning` = ?,`progress_learning` = ?,`total_vocab` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LessonData value) {
        stmt.bindLong(1, value.getLessonId());
        stmt.bindLong(2, value.getBookGradle());
        if (value.getLessonTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLessonTitle());
        }
        stmt.bindLong(4, value.getLessonPriority());
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getProgressLearning());
        stmt.bindLong(7, value.getTotalVocab());
        stmt.bindLong(8, value.getLessonId());
      }
    };
  }

  @Override
  public Single<List<Long>> insert(final LessonData... t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfLessonData.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final List<LessonData> t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfLessonData.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final LessonData... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLessonData.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final List<LessonData> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfLessonData.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable update(final LessonData... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLessonData.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final List<LessonData> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfLessonData.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Observable<List<LessonData>> loadAllLesson() {
    final String _sql = "SELECT * FROM lesson_of_book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"lesson_of_book"}, new Callable<List<LessonData>>() {
      @Override
      public List<LessonData> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfBookGradle = CursorUtil.getColumnIndexOrThrow(_cursor, "book_gradle");
          final int _cursorIndexOfLessonTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_title");
          final int _cursorIndexOfLessonPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_priority");
          final int _cursorIndexOfIsLearning = CursorUtil.getColumnIndexOrThrow(_cursor, "is_learning");
          final int _cursorIndexOfProgressLearning = CursorUtil.getColumnIndexOrThrow(_cursor, "progress_learning");
          final int _cursorIndexOfTotalVocab = CursorUtil.getColumnIndexOrThrow(_cursor, "total_vocab");
          final List<LessonData> _result = new ArrayList<LessonData>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final LessonData _item;
            _item = new LessonData();
            final int _tmpLessonId;
            _tmpLessonId = _cursor.getInt(_cursorIndexOfLessonId);
            _item.setLessonId(_tmpLessonId);
            final int _tmpBookGradle;
            _tmpBookGradle = _cursor.getInt(_cursorIndexOfBookGradle);
            _item.setBookGradle(_tmpBookGradle);
            final String _tmpLessonTitle;
            _tmpLessonTitle = _cursor.getString(_cursorIndexOfLessonTitle);
            _item.setLessonTitle(_tmpLessonTitle);
            final int _tmpLessonPriority;
            _tmpLessonPriority = _cursor.getInt(_cursorIndexOfLessonPriority);
            _item.setLessonPriority(_tmpLessonPriority);
            final boolean _tmpIsLearning;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearning);
            _tmpIsLearning = _tmp != 0;
            _item.setLearning(_tmpIsLearning);
            final int _tmpProgressLearning;
            _tmpProgressLearning = _cursor.getInt(_cursorIndexOfProgressLearning);
            _item.setProgressLearning(_tmpProgressLearning);
            final int _tmpTotalVocab;
            _tmpTotalVocab = _cursor.getInt(_cursorIndexOfTotalVocab);
            _item.setTotalVocab(_tmpTotalVocab);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
