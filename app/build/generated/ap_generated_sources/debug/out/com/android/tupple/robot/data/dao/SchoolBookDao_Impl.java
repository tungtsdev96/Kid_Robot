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
import com.android.tupple.robot.data.entity.SchoolBook;
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
public final class SchoolBookDao_Impl extends SchoolBookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SchoolBook> __insertionAdapterOfSchoolBook;

  private final EntityDeletionOrUpdateAdapter<SchoolBook> __deletionAdapterOfSchoolBook;

  private final EntityDeletionOrUpdateAdapter<SchoolBook> __updateAdapterOfSchoolBook;

  public SchoolBookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSchoolBook = new EntityInsertionAdapter<SchoolBook>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `school_book` (`_id`,`book_gradle`,`total_lesson`,`book_title`,`is_learning`,`image`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SchoolBook value) {
        stmt.bindLong(1, value.getIdBook());
        stmt.bindLong(2, value.getGradle());
        stmt.bindLong(3, value.getTotalLesson());
        if (value.getNameBook() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNameBook());
        }
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getImageBook() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImageBook());
        }
      }
    };
    this.__deletionAdapterOfSchoolBook = new EntityDeletionOrUpdateAdapter<SchoolBook>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `school_book` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SchoolBook value) {
        stmt.bindLong(1, value.getIdBook());
      }
    };
    this.__updateAdapterOfSchoolBook = new EntityDeletionOrUpdateAdapter<SchoolBook>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `school_book` SET `_id` = ?,`book_gradle` = ?,`total_lesson` = ?,`book_title` = ?,`is_learning` = ?,`image` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SchoolBook value) {
        stmt.bindLong(1, value.getIdBook());
        stmt.bindLong(2, value.getGradle());
        stmt.bindLong(3, value.getTotalLesson());
        if (value.getNameBook() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNameBook());
        }
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getImageBook() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getImageBook());
        }
        stmt.bindLong(7, value.getIdBook());
      }
    };
  }

  @Override
  public Single<List<Long>> insert(final SchoolBook... t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfSchoolBook.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final List<SchoolBook> t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfSchoolBook.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final SchoolBook... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSchoolBook.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final List<SchoolBook> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfSchoolBook.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable update(final SchoolBook... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSchoolBook.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final List<SchoolBook> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfSchoolBook.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Observable<List<SchoolBook>> loadAllBook() {
    final String _sql = "SELECT * FROM school_book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"school_book"}, new Callable<List<SchoolBook>>() {
      @Override
      public List<SchoolBook> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdBook = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfGradle = CursorUtil.getColumnIndexOrThrow(_cursor, "book_gradle");
          final int _cursorIndexOfTotalLesson = CursorUtil.getColumnIndexOrThrow(_cursor, "total_lesson");
          final int _cursorIndexOfNameBook = CursorUtil.getColumnIndexOrThrow(_cursor, "book_title");
          final int _cursorIndexOfIsLearning = CursorUtil.getColumnIndexOrThrow(_cursor, "is_learning");
          final int _cursorIndexOfImageBook = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final List<SchoolBook> _result = new ArrayList<SchoolBook>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SchoolBook _item;
            _item = new SchoolBook();
            final int _tmpIdBook;
            _tmpIdBook = _cursor.getInt(_cursorIndexOfIdBook);
            _item.setIdBook(_tmpIdBook);
            final int _tmpGradle;
            _tmpGradle = _cursor.getInt(_cursorIndexOfGradle);
            _item.setGradle(_tmpGradle);
            final int _tmpTotalLesson;
            _tmpTotalLesson = _cursor.getInt(_cursorIndexOfTotalLesson);
            _item.setTotalLesson(_tmpTotalLesson);
            final String _tmpNameBook;
            _tmpNameBook = _cursor.getString(_cursorIndexOfNameBook);
            _item.setNameBook(_tmpNameBook);
            final boolean _tmpIsLearning;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLearning);
            _tmpIsLearning = _tmp != 0;
            _item.setLearning(_tmpIsLearning);
            final String _tmpImageBook;
            _tmpImageBook = _cursor.getString(_cursorIndexOfImageBook);
            _item.setImageBook(_tmpImageBook);
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
