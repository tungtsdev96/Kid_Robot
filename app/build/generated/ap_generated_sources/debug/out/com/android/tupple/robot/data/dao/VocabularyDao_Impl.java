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
import com.android.tupple.robot.data.entity.Vocabulary;
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
public final class VocabularyDao_Impl extends VocabularyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vocabulary> __insertionAdapterOfVocabulary;

  private final EntityDeletionOrUpdateAdapter<Vocabulary> __deletionAdapterOfVocabulary;

  private final EntityDeletionOrUpdateAdapter<Vocabulary> __updateAdapterOfVocabulary;

  public VocabularyDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVocabulary = new EntityInsertionAdapter<Vocabulary>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `vocabulary` (`_id`,`vocab_en`,`vocab_vi`,`image_url`,`total_image`,`audio_url`,`score_correct`,`score_wrong`,`topic_id`,`lesson_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vocabulary value) {
        stmt.bindLong(1, value.getVocabId());
        if (value.getVocabEn() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getVocabEn());
        }
        if (value.getVocabVi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVocabVi());
        }
        if (value.getImageUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getImageUrl());
        }
        stmt.bindLong(5, value.getTotalImage());
        if (value.getAudioUrl() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAudioUrl());
        }
        stmt.bindLong(7, value.getScoreCorrect());
        stmt.bindLong(8, value.getScoreWrong());
        stmt.bindLong(9, value.getTopicId());
        stmt.bindLong(10, value.getLessonId());
      }
    };
    this.__deletionAdapterOfVocabulary = new EntityDeletionOrUpdateAdapter<Vocabulary>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `vocabulary` WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vocabulary value) {
        stmt.bindLong(1, value.getVocabId());
      }
    };
    this.__updateAdapterOfVocabulary = new EntityDeletionOrUpdateAdapter<Vocabulary>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `vocabulary` SET `_id` = ?,`vocab_en` = ?,`vocab_vi` = ?,`image_url` = ?,`total_image` = ?,`audio_url` = ?,`score_correct` = ?,`score_wrong` = ?,`topic_id` = ?,`lesson_id` = ? WHERE `_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vocabulary value) {
        stmt.bindLong(1, value.getVocabId());
        if (value.getVocabEn() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getVocabEn());
        }
        if (value.getVocabVi() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getVocabVi());
        }
        if (value.getImageUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getImageUrl());
        }
        stmt.bindLong(5, value.getTotalImage());
        if (value.getAudioUrl() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAudioUrl());
        }
        stmt.bindLong(7, value.getScoreCorrect());
        stmt.bindLong(8, value.getScoreWrong());
        stmt.bindLong(9, value.getTopicId());
        stmt.bindLong(10, value.getLessonId());
        stmt.bindLong(11, value.getVocabId());
      }
    };
  }

  @Override
  public Single<List<Long>> insert(final Vocabulary... t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfVocabulary.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final List<Vocabulary> t) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfVocabulary.insertAndReturnIdsList(t);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable delete(final Vocabulary... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfVocabulary.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final List<Vocabulary> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfVocabulary.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Completable update(final Vocabulary... t) {
    return Completable.fromCallable(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVocabulary.handleMultiple(t);
          __db.setTransactionSuccessful();
          return null;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final List<Vocabulary> t) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfVocabulary.handleMultiple(t);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Observable<List<Vocabulary>> getListVocabularyByTopicId(final int topicId) {
    final String _sql = "SELECT * FROM vocabulary WHERE topic_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, topicId);
    return RxRoom.createObservable(__db, false, new String[]{"vocabulary"}, new Callable<List<Vocabulary>>() {
      @Override
      public List<Vocabulary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVocabId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfVocabEn = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_en");
          final int _cursorIndexOfVocabVi = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_vi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
          final int _cursorIndexOfTotalImage = CursorUtil.getColumnIndexOrThrow(_cursor, "total_image");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audio_url");
          final int _cursorIndexOfScoreCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "score_correct");
          final int _cursorIndexOfScoreWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "score_wrong");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topic_id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_id");
          final List<Vocabulary> _result = new ArrayList<Vocabulary>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Vocabulary _item;
            _item = new Vocabulary();
            final int _tmpVocabId;
            _tmpVocabId = _cursor.getInt(_cursorIndexOfVocabId);
            _item.setVocabId(_tmpVocabId);
            final String _tmpVocabEn;
            _tmpVocabEn = _cursor.getString(_cursorIndexOfVocabEn);
            _item.setVocabEn(_tmpVocabEn);
            final String _tmpVocabVi;
            _tmpVocabVi = _cursor.getString(_cursorIndexOfVocabVi);
            _item.setVocabVi(_tmpVocabVi);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _item.setImageUrl(_tmpImageUrl);
            final int _tmpTotalImage;
            _tmpTotalImage = _cursor.getInt(_cursorIndexOfTotalImage);
            _item.setTotalImage(_tmpTotalImage);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            _item.setAudioUrl(_tmpAudioUrl);
            final int _tmpScoreCorrect;
            _tmpScoreCorrect = _cursor.getInt(_cursorIndexOfScoreCorrect);
            _item.setScoreCorrect(_tmpScoreCorrect);
            final int _tmpScoreWrong;
            _tmpScoreWrong = _cursor.getInt(_cursorIndexOfScoreWrong);
            _item.setScoreWrong(_tmpScoreWrong);
            final int _tmpTopicId;
            _tmpTopicId = _cursor.getInt(_cursorIndexOfTopicId);
            _item.setTopicId(_tmpTopicId);
            final int _tmpLessonId;
            _tmpLessonId = _cursor.getInt(_cursorIndexOfLessonId);
            _item.setLessonId(_tmpLessonId);
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

  @Override
  public Observable<List<Vocabulary>> getListVocabularyByLessonId(final int lessonId) {
    final String _sql = "SELECT * FROM vocabulary WHERE lesson_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, lessonId);
    return RxRoom.createObservable(__db, false, new String[]{"vocabulary"}, new Callable<List<Vocabulary>>() {
      @Override
      public List<Vocabulary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVocabId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfVocabEn = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_en");
          final int _cursorIndexOfVocabVi = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_vi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
          final int _cursorIndexOfTotalImage = CursorUtil.getColumnIndexOrThrow(_cursor, "total_image");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audio_url");
          final int _cursorIndexOfScoreCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "score_correct");
          final int _cursorIndexOfScoreWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "score_wrong");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topic_id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_id");
          final List<Vocabulary> _result = new ArrayList<Vocabulary>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Vocabulary _item;
            _item = new Vocabulary();
            final int _tmpVocabId;
            _tmpVocabId = _cursor.getInt(_cursorIndexOfVocabId);
            _item.setVocabId(_tmpVocabId);
            final String _tmpVocabEn;
            _tmpVocabEn = _cursor.getString(_cursorIndexOfVocabEn);
            _item.setVocabEn(_tmpVocabEn);
            final String _tmpVocabVi;
            _tmpVocabVi = _cursor.getString(_cursorIndexOfVocabVi);
            _item.setVocabVi(_tmpVocabVi);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _item.setImageUrl(_tmpImageUrl);
            final int _tmpTotalImage;
            _tmpTotalImage = _cursor.getInt(_cursorIndexOfTotalImage);
            _item.setTotalImage(_tmpTotalImage);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            _item.setAudioUrl(_tmpAudioUrl);
            final int _tmpScoreCorrect;
            _tmpScoreCorrect = _cursor.getInt(_cursorIndexOfScoreCorrect);
            _item.setScoreCorrect(_tmpScoreCorrect);
            final int _tmpScoreWrong;
            _tmpScoreWrong = _cursor.getInt(_cursorIndexOfScoreWrong);
            _item.setScoreWrong(_tmpScoreWrong);
            final int _tmpTopicId;
            _tmpTopicId = _cursor.getInt(_cursorIndexOfTopicId);
            _item.setTopicId(_tmpTopicId);
            final int _tmpLessonId;
            _tmpLessonId = _cursor.getInt(_cursorIndexOfLessonId);
            _item.setLessonId(_tmpLessonId);
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

  @Override
  public Observable<List<Vocabulary>> makeListVocabularyLearningFromTopic(final int topicId) {
    final String _sql = "SELECT * FROM vocabulary WHERE topic_id = ? AND (score_correct - score_wrong) < 3  ORDER BY score_wrong ASC LIMIT 4";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, topicId);
    return RxRoom.createObservable(__db, false, new String[]{"vocabulary"}, new Callable<List<Vocabulary>>() {
      @Override
      public List<Vocabulary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVocabId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfVocabEn = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_en");
          final int _cursorIndexOfVocabVi = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_vi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
          final int _cursorIndexOfTotalImage = CursorUtil.getColumnIndexOrThrow(_cursor, "total_image");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audio_url");
          final int _cursorIndexOfScoreCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "score_correct");
          final int _cursorIndexOfScoreWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "score_wrong");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topic_id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_id");
          final List<Vocabulary> _result = new ArrayList<Vocabulary>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Vocabulary _item;
            _item = new Vocabulary();
            final int _tmpVocabId;
            _tmpVocabId = _cursor.getInt(_cursorIndexOfVocabId);
            _item.setVocabId(_tmpVocabId);
            final String _tmpVocabEn;
            _tmpVocabEn = _cursor.getString(_cursorIndexOfVocabEn);
            _item.setVocabEn(_tmpVocabEn);
            final String _tmpVocabVi;
            _tmpVocabVi = _cursor.getString(_cursorIndexOfVocabVi);
            _item.setVocabVi(_tmpVocabVi);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _item.setImageUrl(_tmpImageUrl);
            final int _tmpTotalImage;
            _tmpTotalImage = _cursor.getInt(_cursorIndexOfTotalImage);
            _item.setTotalImage(_tmpTotalImage);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            _item.setAudioUrl(_tmpAudioUrl);
            final int _tmpScoreCorrect;
            _tmpScoreCorrect = _cursor.getInt(_cursorIndexOfScoreCorrect);
            _item.setScoreCorrect(_tmpScoreCorrect);
            final int _tmpScoreWrong;
            _tmpScoreWrong = _cursor.getInt(_cursorIndexOfScoreWrong);
            _item.setScoreWrong(_tmpScoreWrong);
            final int _tmpTopicId;
            _tmpTopicId = _cursor.getInt(_cursorIndexOfTopicId);
            _item.setTopicId(_tmpTopicId);
            final int _tmpLessonId;
            _tmpLessonId = _cursor.getInt(_cursorIndexOfLessonId);
            _item.setLessonId(_tmpLessonId);
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

  @Override
  public Observable<List<Vocabulary>> makeListVocabularyLearningFromLesson(final int lessonId) {
    final String _sql = "SELECT * FROM vocabulary WHERE lesson_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, lessonId);
    return RxRoom.createObservable(__db, false, new String[]{"vocabulary"}, new Callable<List<Vocabulary>>() {
      @Override
      public List<Vocabulary> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVocabId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfVocabEn = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_en");
          final int _cursorIndexOfVocabVi = CursorUtil.getColumnIndexOrThrow(_cursor, "vocab_vi");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
          final int _cursorIndexOfTotalImage = CursorUtil.getColumnIndexOrThrow(_cursor, "total_image");
          final int _cursorIndexOfAudioUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "audio_url");
          final int _cursorIndexOfScoreCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "score_correct");
          final int _cursorIndexOfScoreWrong = CursorUtil.getColumnIndexOrThrow(_cursor, "score_wrong");
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topic_id");
          final int _cursorIndexOfLessonId = CursorUtil.getColumnIndexOrThrow(_cursor, "lesson_id");
          final List<Vocabulary> _result = new ArrayList<Vocabulary>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Vocabulary _item;
            _item = new Vocabulary();
            final int _tmpVocabId;
            _tmpVocabId = _cursor.getInt(_cursorIndexOfVocabId);
            _item.setVocabId(_tmpVocabId);
            final String _tmpVocabEn;
            _tmpVocabEn = _cursor.getString(_cursorIndexOfVocabEn);
            _item.setVocabEn(_tmpVocabEn);
            final String _tmpVocabVi;
            _tmpVocabVi = _cursor.getString(_cursorIndexOfVocabVi);
            _item.setVocabVi(_tmpVocabVi);
            final String _tmpImageUrl;
            _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            _item.setImageUrl(_tmpImageUrl);
            final int _tmpTotalImage;
            _tmpTotalImage = _cursor.getInt(_cursorIndexOfTotalImage);
            _item.setTotalImage(_tmpTotalImage);
            final String _tmpAudioUrl;
            _tmpAudioUrl = _cursor.getString(_cursorIndexOfAudioUrl);
            _item.setAudioUrl(_tmpAudioUrl);
            final int _tmpScoreCorrect;
            _tmpScoreCorrect = _cursor.getInt(_cursorIndexOfScoreCorrect);
            _item.setScoreCorrect(_tmpScoreCorrect);
            final int _tmpScoreWrong;
            _tmpScoreWrong = _cursor.getInt(_cursorIndexOfScoreWrong);
            _item.setScoreWrong(_tmpScoreWrong);
            final int _tmpTopicId;
            _tmpTopicId = _cursor.getInt(_cursorIndexOfTopicId);
            _item.setTopicId(_tmpTopicId);
            final int _tmpLessonId;
            _tmpLessonId = _cursor.getInt(_cursorIndexOfLessonId);
            _item.setLessonId(_tmpLessonId);
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
