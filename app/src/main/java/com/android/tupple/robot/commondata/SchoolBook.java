package com.android.tupple.robot.commondata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class SchoolBook {

    public String idBook;
    public String nameBook;
    public String imageBook;

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
