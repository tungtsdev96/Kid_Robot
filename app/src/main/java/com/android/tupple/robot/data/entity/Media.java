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
    private String audio_thumbnail;
    private String description;

    protected Media(Parcel in) {
        id = in.readInt();
        title = in.readString();
        is_audio = in.readByte() != 0;
        media_url = in.readString();
        audio_thumbnail = in.readString();
        description = in.readString();
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

    public String getAudio_thumbnail() {
        return audio_thumbnail;
    }

    public void setAudio_thumbnail(String audio_thumbnail) {
        this.audio_thumbnail = audio_thumbnail;
    }

    public Media(String title, boolean is_audio, String media_url, String audio_thumbnail, String description) {
        this.title = title;
        this.is_audio = is_audio;
        this.media_url = media_url;
        this.audio_thumbnail = audio_thumbnail;
        this.description = description;
    }


    public Media(String title, String media_url, String thumbnail, String description) {
        this.title = title;
        this.media_url = media_url;
        this.audio_thumbnail = thumbnail;
        this.description = description;
    }

    public Media(int id, String title, boolean is_audio, String media_url, String audio_thumbnail, String description) {
        this.id = id;
        this.title = title;
        this.is_audio = is_audio;
        this.media_url = media_url;
        this.audio_thumbnail = audio_thumbnail;
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

    public String getThumbnail() {
        return audio_thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.audio_thumbnail = thumbnail;
    }

    public static List<Media> fakeAudioData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(1, "Anh Khác Hay Em Khác", true,
                "/storage/71ED-19F1/New Folder/Anh Khác Hay Em Khác_Khắc Việt_-1074066567.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/1.jpg",
                "Description for media object #1"));
        media.add(new Media(2, "Apologize", true,
                "/storage/71ED-19F1/New Folder/Apologize.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/2.jpg",
                "Description for media object #1"));
        media.add(new Media(3, "Chi Can Em Hanh Phuc - Ho Quang Hieu", true,
                "/storage/71ED-19F1/New Folder/Chi Can Em Hanh Phuc - Ho Quang Hieu.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/3.jpg",
                "Description for media object #1"));
        media.add(new Media(4, "Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao", true,
                "/storage/71ED-19F1/New Folder/Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/4.jpg",
                "Description for media object #1"));
        media.add(new Media(5, "Cam On Nguoi Da Roi Xa Toi", true,
                "/storage/71ED-19F1/New Folder/Cam On Nguoi Da Roi Xa Toi - Pham Hong Phuoc.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/5.jpg",
                "Description for media object #1"));
        media.add(new Media(6, "Bệnh Của Anh_Khói", true,
                "/storage/71ED-19F1/New Folder/Bệnh Của Anh_Khói_-1075451153.mp3",
                "/storage/71ED-19F1/New Folder (2)/Wallpaper/6.jpg",
                "Description for media object #1"));
        return media;
    }

    public static List<Media> fakeVideoData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(1, "Kimetsu No Yaiba 1", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 1.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1"));
        media.add(new Media(2, "Kimetsu No Yaiba 2", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 2.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1"));
        media.add(new Media(3, "Kimetsu No Yaiba 3", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 3.mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1"));
        media.add(new Media(4, "Kimetsu No Yaiba 4", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 4 .mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1"));
        media.add(new Media(5, "Kimetsu No Yaiba 5", false,
                "/storage/71ED-19F1/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 5 .mp4",
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png",
                "Description for media object #1"));
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
        dest.writeString(audio_thumbnail);
        dest.writeString(description);
    }
}