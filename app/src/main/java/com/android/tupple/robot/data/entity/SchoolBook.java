package com.android.tupple.robot.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static com.android.tupple.robot.data.entity.SchoolBook.TABLE_NAME;

/**
 * Created by tungts on 2020-01-15.
 *  TODO using index to speed up queries
 */

@Entity(tableName = TABLE_NAME)
public class SchoolBook implements Parcelable {

    public static final String TABLE_NAME = "school_book";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.SchoolBook._ID)
    private int idBook;

    @ColumnInfo(name = Columns.SchoolBook.BOOK_GRADLE)
    private int gradle;

    @ColumnInfo(name = Columns.SchoolBook.TOTAL_LESSON)
    private int totalLesson;

    @ColumnInfo(name = Columns.SchoolBook.BOOK_TITLE)
    private String nameBook;

    @ColumnInfo(name = Columns.SchoolBook.IS_LEARNING)
    private boolean isLearning;

    @ColumnInfo(name = Columns.SchoolBook.IMAGE)
    private String imageBook;

//    @Ignore - ignore field
//    Bitmap picture;

    public SchoolBook() {
    }

    protected SchoolBook(Parcel in) {
        idBook = in.readInt();
        gradle = in.readInt();
        totalLesson = in.readInt();
        nameBook = in.readString();
        isLearning = in.readByte() != 0;
        imageBook = in.readString();
    }

    public static final Creator<SchoolBook> CREATOR = new Creator<SchoolBook>() {
        @Override
        public SchoolBook createFromParcel(Parcel in) {
            return new SchoolBook(in);
        }

        @Override
        public SchoolBook[] newArray(int size) {
            return new SchoolBook[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idBook);
        parcel.writeInt(gradle);
        parcel.writeInt(totalLesson);
        parcel.writeString(nameBook);
        parcel.writeByte((byte) (isLearning ? 1 : 0));
        parcel.writeString(imageBook);
    }

    public static List<SchoolBook> fakeData() {
        SchoolBook book = new SchoolBook();
        book.idBook = 1;
        book.imageBook = "adsa";
        book.nameBook = "SGK";

        List<SchoolBook> schoolBooks = new ArrayList<>();
        schoolBooks.add(book);
        schoolBooks.add(book);
        schoolBooks.add(book);
        schoolBooks.add(book);
        schoolBooks.add(book);

        return schoolBooks;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getGradle() {
        return gradle;
    }

    public void setGradle(int gradle) {
        this.gradle = gradle;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public boolean isLearning() {
        return isLearning;
    }

    public void setLearning(boolean learning) {
        isLearning = learning;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }
}
