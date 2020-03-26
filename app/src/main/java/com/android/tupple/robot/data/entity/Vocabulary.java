package com.android.tupple.robot.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.android.tupple.robot.data.file.ImageUtils;
import com.android.tupple.robot.data.file.SoundUtils;

import static com.android.tupple.robot.data.entity.Vocabulary.TABLE_NAME;

/**
 * Created by tungts on 2020-01-15.
 */

@Entity(tableName = TABLE_NAME)
public class Vocabulary {

    public static final String TABLE_NAME = "vocabulary";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.Vocabulary._ID)
    private int vocabId;

    @ColumnInfo(name = Columns.Vocabulary.VOCAB_EN)
    private String vocabEn;

    @ColumnInfo(name = Columns.Vocabulary.VOCAB_VI)
    private String vocabVi;

    @ColumnInfo(name = Columns.Vocabulary.TOTAL_IMAGE, defaultValue = "1")
    private int totalImage;

    @ColumnInfo(name = Columns.Vocabulary.SCORE_CORRECT, defaultValue = "0")
    private int scoreCorrect;

    @ColumnInfo(name = Columns.Vocabulary.SCORE_WRONG, defaultValue = "0")
    private int scoreWrong;

    @ColumnInfo(name = Columns.Vocabulary.TOPIC_ID, defaultValue = "-1")
    private int topicId;

    @ColumnInfo(name = Columns.Vocabulary.LESSON_ID, defaultValue = "-1")
    private int lessonId;

    public int getVocabId() {
        return vocabId;
    }

    public void setVocabId(int vocabId) {
        this.vocabId = vocabId;
    }

    public String getVocabEn() {
        return vocabEn;
    }

    public void setVocabEn(String vocabEn) {
        this.vocabEn = vocabEn;
    }

    public String getVocabVi() {
        return vocabVi;
    }

    public void setVocabVi(String vocabVi) {
        this.vocabVi = vocabVi;
    }

    public String getImageUrl() {
        if (topicId > 0) {
            return ImageUtils.getUrlImageTopic(vocabEn.toLowerCase(), topicId);
        } else {
            return ImageUtils.getUrlImageLesson(vocabEn.toLowerCase(), lessonId, totalImage);
        }
    }

    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
    }

    public String getAudioUrl() {
        if (topicId > 0) {
            return SoundUtils.getUrlSoundTopic(vocabEn.toLowerCase(), topicId);
        } else {
            return null;
        }
    }

    public int getScoreCorrect() {
        return scoreCorrect;
    }

    public void setScoreCorrect(int scoreCorrect) {
        this.scoreCorrect = scoreCorrect;
    }

    public int getScoreWrong() {
        return scoreWrong;
    }

    public void setScoreWrong(int scoreWrong) {
        this.scoreWrong = scoreWrong;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

}
