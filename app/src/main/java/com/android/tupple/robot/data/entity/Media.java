package com.android.tupple.robot.data.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Media implements Parcelable {
    private int id;
    private String title;
    private boolean is_audio;
    private String media_url;
    private String description;
    private boolean is_local;
    protected Media(Parcel in) {
        id = in.readInt();
        title = in.readString();
        is_audio = in.readByte() != 0;
        media_url = in.readString();
        description = in.readString();
        is_local = in.readByte() != 0;
    }

    public boolean isIs_local() {
        return is_local;
    }

    public void setIs_local(boolean is_local) {
        this.is_local = is_local;
    }


    public Media(int id, String title, boolean is_audio, String media_url, String description, boolean is_local) {
        this.id = id;
        this.title = title;
        this.is_audio = is_audio;
        this.media_url = media_url;
        this.description = description;
        this.is_local = is_local;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public boolean isIs_audio() {
        return is_audio;
    }

    public void setIs_audio(boolean is_audio) {
        this.is_audio = is_audio;
    }


    public Media(String title, boolean is_audio, String media_url,  String description) {
        this.title = title;
        this.is_audio = is_audio;
        this.media_url = media_url;

        this.description = description;
    }


    public Media(String title, String media_url, String description) {
        this.title = title;
        this.media_url = media_url;
        this.description = description;
    }

    public Media(int id, String title, boolean is_audio, String media_url, String description) {
        this.id = id;
        this.title = title;
        this.is_audio = is_audio;
        this.media_url = media_url;
        this.description = description;
    }

    public Media() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static List<Media> fakeAudioData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(1, "Anh Khác Hay Em Khác", true,
                "/storage/71ED-19F1/New Folder/Anh Khác Hay Em Khác_Khắc Việt_-1074066567.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(2, "Apologize", true,
                "/storage/71ED-19F1/New Folder/Apologize.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(3, "Chi Can Em Hanh Phuc - Ho Quang Hieu", true,
                "/storage/71ED-19F1/New Folder/Chi Can Em Hanh Phuc - Ho Quang Hieu.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(4, "Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao", true,
                "/storage/71ED-19F1/New Folder/Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(5, "Cam On Nguoi Da Roi Xa Toi", true,
                "/storage/71ED-19F1/New Folder/Cam On Nguoi Da Roi Xa Toi - Pham Hong Phuoc.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(6, "Bệnh Của Anh_Khói", true,
                "/storage/71ED-19F1/New Folder/Bệnh Của Anh_Khói_-1075451153.mp3",
                "Description for media object #1",
                true));
        return media;
    }

    public static List<Media> fakeVideoData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(1, "Kimetsu No Yaiba 1", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 1.mp4",
                "Description for media object #1",
                true));
        media.add(new Media(2, "Kimetsu No Yaiba 2", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 2.mp4",
                "Description for media object #1",
                true));
        media.add(new Media(3, "Kimetsu No Yaiba 3", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 3.mp4",
                "Description for media object #1",
                true));
        media.add(new Media(4, "Kimetsu No Yaiba 4", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 4 .mp4",
                "Description for media object #1",
                true));
        media.add(new Media(5, "Kimetsu No Yaiba 5", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 5 .mp4",
                "Description for media object #1",
                true));
        return media;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeByte((byte) (is_audio ? 1 : 0));
        dest.writeString(media_url);
        dest.writeString(description);
        dest.writeByte((byte) (is_local ? 1 : 0));
    }
}