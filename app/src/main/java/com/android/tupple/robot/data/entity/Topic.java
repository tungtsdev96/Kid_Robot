package com.android.tupple.robot.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.android.tupple.robot.data.entity.Topic.TABLE_NAME;

/**
 * Created by tungts on 2020-01-15.
 */

@Entity(tableName = TABLE_NAME)
public class Topic implements Parcelable {

    public static final String TABLE_NAME = "topic";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.Topic._ID)
    private int topicId;

    @ColumnInfo(name = Columns.Topic.TOPIC_TITLE)
    private String topicTitle;

    @ColumnInfo(name = Columns.Topic.IS_LEARNING, defaultValue = "0")
    private boolean isLearning;

    @ColumnInfo(name = Columns.Topic.PROGRESS_LEARNING, defaultValue = "0")
    private int progressLearning;

    @ColumnInfo(name = Columns.Topic.TOTAL_VOCAB)
    private int totalVocab;

    public Topic(){}

    protected Topic(Parcel in) {
        topicId = in.readInt();
        topicTitle = in.readString();
        isLearning = in.readByte() != 0;
        progressLearning = in.readInt();
        totalVocab = in.readInt();
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

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(topicId);
        dest.writeString(topicTitle);
        dest.writeByte((byte) (isLearning ? 1 : 0));
        dest.writeInt(progressLearning);
        dest.writeInt(totalVocab);
    }
}
