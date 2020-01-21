package com.android.tupple.robot.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class SchoolBook implements Parcelable {

    public String idBook;
    public String nameBook;
    public String imageBook;

    protected SchoolBook(Parcel in) {
        idBook = in.readString();
        nameBook = in.readString();
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

    public SchoolBook() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBook);
        dest.writeString(nameBook);
        dest.writeString(imageBook);
    }

    public static List<SchoolBook> fakeData() {
        SchoolBook book = new SchoolBook();
        book.idBook = "1";
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
}
