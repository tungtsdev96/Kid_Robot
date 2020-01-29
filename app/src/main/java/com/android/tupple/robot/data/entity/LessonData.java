package com.android.tupple.robot.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static com.android.tupple.robot.data.entity.LessonData.TABLE_NAME;

/**
 * Created by tungts on 2020-01-15.
 */

@Entity(tableName = TABLE_NAME)
public class LessonData implements Parcelable {

    public static final String TABLE_NAME = "lesson_of_book";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.LessonOfBook._ID)
    private int lessonId;

    @ColumnInfo(name = Columns.LessonOfBook.BOOK_GRADLE)
    private int bookGradle;

    @ColumnInfo(name = Columns.LessonOfBook.LESSON_TITLE)
    private String lessonTitle;

    @ColumnInfo(name = Columns.LessonOfBook.IS_LEARNING)
    private boolean isLearning;

    @ColumnInfo(name = Columns.LessonOfBook.PROGRESS_LEARNING)
    private int progressLearning;

    @ColumnInfo(name = Columns.LessonOfBook.TOTAL_VOCAB)
    private int totalVocab;

    public LessonData() {}

    protected LessonData(Parcel in) {
        lessonId = in.readInt();
        bookGradle = in.readInt();
        lessonTitle = in.readString();
        isLearning = in.readByte() != 0;
        progressLearning = in.readInt();
        totalVocab = in.readInt();
    }

    public static final Creator<LessonData> CREATOR = new Creator<LessonData>() {
        @Override
        public LessonData createFromParcel(Parcel in) {
            return new LessonData(in);
        }

        @Override
        public LessonData[] newArray(int size) {
            return new LessonData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(lessonId);
        dest.writeInt(bookGradle);
        dest.writeString(lessonTitle);
        dest.writeByte((byte) (isLearning ? 1 : 0));
        dest.writeInt(progressLearning);
        dest.writeInt(totalVocab);
    }

    public static List<LessonData> fakeData() {
        LessonData lessonData = new LessonData();
        lessonData.lessonId = 1;
        lessonData.bookGradle = 1;
        lessonData.lessonTitle = "Hello World!!";
        lessonData.isLearning = true;
        lessonData.progressLearning = 50;
        lessonData.totalVocab = 15;

        List<LessonData> list = new ArrayList<>();
        list.add(lessonData);
        list.add(lessonData);
        list.add(lessonData);
        list.add(lessonData);
        list.add(lessonData);
        list.add(lessonData);

        return list;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getBookGradle() {
        return bookGradle;
    }

    public void setBookGradle(int bookGradle) {
        this.bookGradle = bookGradle;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public boolean isLearning() {
        return isLearning;
    }

    public void setLearning(boolean learning) {
        isLearning = learning;
    }

    public int getProgressLearning() {
        return progressLearning;
    }

    public void setProgressLearning(int progressLearning) {
        this.progressLearning = progressLearning;
    }

    public int getTotalVocab() {
        return totalVocab;
    }

    public void setTotalVocab(int totalVocab) {
        this.totalVocab = totalVocab;
    }
}
