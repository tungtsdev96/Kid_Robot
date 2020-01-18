package com.android.tupple.robot.common.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tungts on 2020-01-15.
 */

public class LessonData implements Parcelable {

    public int lessonId;
    public int lessonLevel;
    public String lessonTitle;
    public boolean isLearning;
    public int progressLearning;
    public int totalVocab;

    public LessonData() {}

    protected LessonData(Parcel in) {
        lessonId = in.readInt();
        lessonLevel = in.readInt();
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
        dest.writeInt(lessonLevel);
        dest.writeString(lessonTitle);
        dest.writeByte((byte) (isLearning ? 1 : 0));
        dest.writeInt(progressLearning);
        dest.writeInt(totalVocab);
    }

    public static List<LessonData> fakeData() {
        LessonData lessonData = new LessonData();
        lessonData.lessonId = 1;
        lessonData.lessonLevel = 1;
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
}
