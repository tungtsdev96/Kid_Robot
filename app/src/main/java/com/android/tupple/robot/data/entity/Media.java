package com.android.tupple.robot.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import static com.android.tupple.robot.data.entity.Media.TABLE_NAME;


@Entity(tableName = "media")
public class Media implements Parcelable {
    public static final String TABLE_NAME = "media";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.Media._ID)
    private int id;

    @ColumnInfo(name = Columns.Media.MEDIA_TITLE)
    private String title;

    @ColumnInfo(name = Columns.Media.MEDIA_TYPE, defaultValue = "true")
    private String media_type;

    @ColumnInfo(name = Columns.Media.MEDIA_URL)
    private String media_url;

    @ColumnInfo(name = Columns.Media.MEDIA_DESCRIPTION)
    private String description;

    public boolean isIs_local() {
        return is_local;
    }

    @ColumnInfo(name = Columns.Media.IS_LOCAL, defaultValue = "true")
    private boolean is_local;

    @ColumnInfo(name = Columns.Media.VIDEO_THUMBNAIL)
    private String thumbnail_video; // thumbnail for video if video not exist in local;

    protected Media(Parcel in) {
        id = in.readInt();
        title = in.readString();
        media_type = in.readString();
        media_url = in.readString();
        description = in.readString();
        is_local = in.readByte() != 0;
        thumbnail_video = in.readString();
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

    public Media(int id, String title, String media_type, String media_url, String description, boolean is_local, String thumbnail_video) {
        this.id = id;
        this.title = title;
        this.media_type = media_type;
        this.media_url = media_url;
        this.description = description;
        this.is_local = is_local;
        this.thumbnail_video = thumbnail_video;
    }

    public Media(int id, String title, String media_type, String media_url, String description, boolean is_local) {
        this.id = id;
        this.title = title;
        this.media_type = media_type;
        this.media_url = media_url;
        this.description = description;
        this.is_local = is_local;
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

    public boolean isLocal() {
        return is_local;
    }

    public void setIs_local(boolean is_local) {
        this.is_local = is_local;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getThumbnail_video() {
        return thumbnail_video;
    }

    public void setThumbnail_video(String thumbnail_video) {
        this.thumbnail_video = thumbnail_video;
    }

    public static List<Media> fakeAudioData() {
        List<Media> media = new ArrayList<>();
//        media.add(new Media(1, "Anh Khác Hay Em Khác", true,
//                "/storage/emulated/0/DataMediaKidRobot/Audio/Anh Khác Hay Em Khác_Khắc Việt_-1074066567.mp3",
//                "Description for media object #1",
//                true));
        media.add(new Media(1,"Apologize", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Apologize.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(2,"Chi Can Em Hanh Phuc - Ho Quang Hieu", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Chi Can Em Hanh Phuc - Ho Quang Hieu.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(3,"Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(4,"Cam On Nguoi Da Roi Xa Toi", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Cam On Nguoi Da Roi Xa Toi - Pham Hong Phuoc.mp3",
                "Description for media object #1",
                true));
//        media.add(new Media(6, "Bệnh Của Anh_Khói", true,
//                "/storage/emulated/0/DataMediaKidRobot/Audio/Bệnh Của Anh_Khói_-1075451153.mp3",
//                "Description for media object #1",
//                true));
        return media;
    }

    public static List<Media> fakeVideoData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(5,"Kimetsu No Yaiba 1", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 1.mp4",
                "Description for media object #1",
                true));
        media.add(new Media(6,"Kimetsu No Yaiba 2", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 2.mp4",
                "Description for media object #2",
                true));
        media.add(new Media(7,"Kimetsu No Yaiba 3", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 3.mp4",
                "Description for media object #3",
                true));
        media.add(new Media(8,"Kimetsu No Yaiba 4", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/Lưỡi Gươm Diệt Quỷ - Kimetsu No Yaiba 4 .mp4",
                "Description for media object #4",
                true));
        media.add(new Media(9,"Kimetsu No Yaiba 5", "video",
                "https://www.youtube.com/watch?v=Kg2MnWw6NqY",
                "Description for media object #5",
                true));
        media.add(new Media(10,"Kimetsu No Yaiba 6.jpg", "video",
                "http://www.appsapk.com/downloading/latest/Facebook-119.0.0.23.70.apk",
                "Description for media object #6",
                false,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
        return media;
    }

    public static List<Media> fakeVideoYoutubeData() {
        List<Media> media = new ArrayList<>();
        media.add(new Media(11,"Head, Shoulders, Knees", "video",
                "https://www.youtube.com/watch?v=h4eueDYPTIg",
                "Description for media object #1",
                true));
        media.add(new Media(12,"Twinkle Twinkle Little Star", "video",
                "https://www.youtube.com/watch?v=yCjJyiqpAuU",
                "Description for media object #2",
                true));
        media.add(new Media(13,"You are my sunshine", "video",
                "https://www.youtube.com/watch?v=kVnerkBSEnk",
                "Description for media object #3",
                true));
        media.add(new Media(14,"Old MacDonald Had A Farm ", "video",
                "https://www.youtube.com/watch?v=_6HzoUcx3eo",
                "Description for media object #4",
                true));
        media.add(new Media(15,"ABC Song with Balloons", "video",
                "https://www.youtube.com/watch?v=uCIsBFV87-U",
                "Description for media object #5",
                true));
        media.add(new Media(16,"Our Favorite Numbers Songs", "video",
                "https://www.youtube.com/watch?v=V_lgJgBbqWE",
                "Description for media object #6",
                false,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
        media.add(new Media(17,"Five Little Ducks", "video",
                "https://www.youtube.com/watch?v=pZw9veQ76fo",
                "Description for media object #6",
                false,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
        media.add(new Media(18,"My Happy Song", "video",
                "https://www.youtube.com/watch?v=ufbOHl1mmYk",
                "Description for media object #6",
                false,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
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
        dest.writeString(media_type);
        dest.writeString(media_url);
        dest.writeString(description);
        dest.writeByte((byte) (is_local ? 1 : 0));
        dest.writeString(thumbnail_video);
    }


}