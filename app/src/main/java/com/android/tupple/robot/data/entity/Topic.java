package com.android.tupple.robot.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tungts on 2020-01-15.
 */

public class Topic implements Parcelable {
    protected Topic(Parcel in) {
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
