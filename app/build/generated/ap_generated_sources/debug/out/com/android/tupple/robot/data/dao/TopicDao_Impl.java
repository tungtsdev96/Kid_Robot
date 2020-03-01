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
import com.android.tupple.robot.data.entity.Topic;
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
public final class TopicDao_Impl extends TopicDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Topic> __insertionAdapterOfTopic;

  private final EntityDeletionOrUpdateAdapter<Topic> __deletionAdapterOfTopic;

  private final EntityDeletionOrUpdateAdapter<Topic> __updateAdapterOfTopic;

  public TopicDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTopic = new EntityInsertionAdapter<Topic>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `topic` (`_id`,`lesson_title`,`is_learning`,`progress_learning`,`total_vocab`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Topic value) {
        stmt.bindLong(1, value.getTopicId());
        if (value.getTopicTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTopicTitle());
        }
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        stmt.bindLong(4, value.getProgressLearning());
        stmt.bindLong(5, value.getTotalVocab());
      }
    };
    this.__deletionAdapterOfTopic = new EntityDeletionOrUpdateAdapter<Topic>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `topic` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Topic value) {
        stmt.bindLong(1, value.getTopicId());
      }
    };
    this.__updateAdapterOfTopic = new EntityDeletionOrUpdateAdapter<Topic>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `topic` SET `_id` = ?,`lesson_title` = ?,`is_learning` = ?,`progress_learning` = ?,`total_vocab` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Topic value) {
        stmt.bindLong(1, value.getTopicId());
        if (value.getTopicTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTopicTitle());
        }
        final int _tmp;
        _tmp = value.isLearning() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        stmt.bindLong(4, value.getProgressLearning());
        stmt.bindLong(5, value.getTotalVocab());
        stmt.bindLong(6, value.getTopicId());
      }
    };
  }

  @Override
  public Single<List<Long>> insert(final Topic... t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfTopic.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final List<Topic> t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfTopic.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final Topic... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTopic.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final List<Topic> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfTopic.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable update(final Topic... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTopic.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final List<Topic> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfTopic.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Observable<List<Topic>> loadAllTopic() {
    final String _sql = "SELECT * FROM topic";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"topic"}, new Callable<List<Topic>>() {
      @Override
      public List<Topic> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfTopicTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_title");
          final int _cursorIndexOfIsLearning = CursorUtil.getColumnIndexOrThrow(_cursor, "is_learning");
          final int _cursorIndexOfProgressLearning = CursorUtil.getColumnIndexOrThrow(_cursor, "progress_learning");
          final int _cursorIndexOfTotalVocab = CursorUtil.getColumnIndexOrThrow(_cursor, "total_vocab");
          final List<Topic> _result = new ArrayList<Topic>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Topic _item;
            _item = new Topic();
            final int _tmpTopicId;
            _tmpTopicId = _cursor.getInt(_cursorIndexOfTopicId);
            _item.setTopicId(_tmpTopicId);
            final String _tmpTopicTitle;
            _tmpTopicTitle = _cursor.getString(_cursorIndexOfTopicTitle);
            _item.setTopicTitle(_tmpTopicTitle);
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
