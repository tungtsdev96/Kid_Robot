package com.android.tupple.robot;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.entity.LessonData;
import com.android.tupple.robot.data.entity.Media;
import com.android.tupple.robot.data.entity.SchoolBook;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.log.CLogger;
import com.android.tupple.robot.utils.ResourceUtils;
import com.android.tupple.robot.utils.Utils;
import com.android.tupple.trigger.TriggerService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tungts on 2020-01-12.
 */

public class KidRobotApplication extends MultiDexApplication {

    private static KidRobotApplication sInstance;

    private Activity mCurrentActivity = null;

    public KidRobotApplication() {
        super();
        sInstance = this;
    }
    public static KidRobotApplication getInstance() {
        return sInstance;
    }


    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initCLogger();
        initTriggerService();

        // test data for school book
//        initBook();
//        initLesson();
//        initVocabLesson();
//
//        // test data for topic
//        initTopic();
//        initVocab();
//
//        ///
//        initMedia();
    }

    private void initBook() {
        SchoolBook book = new SchoolBook();
        book.setIdBook(1);
        book.setGradle(1);
        book.setLearning(false);
        book.setNameBook("SGK Tiếng Anh 1");
        KidRobotDatabase.getInstance(getApplicationContext())
                .schoolBookDao().insert(book).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void initLesson() {
        Context mContext = getApplicationContext();
        try {
            ArrayList<LessonData> listLesson = readJsonStreamLesson(mContext.getResources().openRawResource(R.raw.book_1));
            for (LessonData lesson : listLesson) {
                lesson.setTotalVocab(lesson.getTotalVocab() - 2);
            }
            KidRobotDatabase.getInstance(mContext).lessonDao()
                    .insert(listLesson).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void initVocabLesson() {
        Context mContext = getApplicationContext();
        for (int i = 1; i <= 20; i++) {
            int index = i;
            Observable.fromCallable(() -> readJsonStreamVocabLesson(mContext.getResources().openRawResource(ResourceUtils.convertNameToRawRes(mContext, "lesson" + index))))
                    .flatMap(listVocabs -> KidRobotDatabase.getInstance(mContext).vocabularyDao().insert(listVocabs).toObservable())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        }
    }

    private void initTopic() {
        // Topic
        Context mContext = getApplicationContext();
        try {
            ArrayList<Topic> items = readJsonStream(mContext.getResources().openRawResource(R.raw.topic));
            KidRobotDatabase.getInstance(mContext).topicDao()
                    .insert(items).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void initVocab() {
        Context mContext = getApplicationContext();
        // Vocabulary
        try {
            ArrayList<Vocabulary> vocabularies = readJsonStreamVocab(getApplicationContext().getResources().openRawResource(R.raw.animals_t));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.alphabet_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.appliances_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.body_parts_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.clothing_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.colors_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.fruits_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.garden_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.job_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.kitchen_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.musical_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.numbers_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.school_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.shapes_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.sports_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.tools_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.transportation_t)));
            vocabularies.addAll(readJsonStreamVocab(mContext.getResources().openRawResource(R.raw.vegetables_t)));
            KidRobotDatabase.getInstance(mContext).vocabularyDao()
                    .insert(vocabularies).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Vocabulary> readJsonStreamVocabLesson(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Vocabulary>>() {
        }.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
    }

    public ArrayList<LessonData> readJsonStreamLesson(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<LessonData>>() {
        }.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
    }

    public ArrayList<Topic> readJsonStream(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Topic>>() {
        }.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
    }

    public ArrayList<Vocabulary> readJsonStreamVocab(InputStream in) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        Type listType = new TypeToken<ArrayList<Vocabulary>>() {
        }.getType();
        return new GsonBuilder().create().fromJson(reader, listType);
    }

    private void initTriggerService() {
        if (Utils.isMyServiceRunning(getApplicationContext(), TriggerService.class)){
            return;
        }

        try {
            Intent intent = new Intent(getApplicationContext(), TriggerService.class);
            startService(intent);
        } catch (Exception e) {
            Log.e(TriggerService.TAG, "Can not start service");
        }
    }

    private void initCLogger() {
        CLog.setLogger(new CLogger() {
            @Override
            public void printSecD(@NonNull String tag, @NonNull String text) {
            }

            @Override
            public void printD(@NonNull String tag, @NonNull String text) {
                Log.d(tag, text);
            }

            @Override
            public void printSecE(@NonNull String tag, @NonNull String text) {
            }

            @Override
            public void printE(@NonNull String tag, @NonNull String text) {
                Log.e(tag, text);
            }
        });
    }

    private void initMedia() {

        List<Media> media = new ArrayList<>();

        media.add(new Media(1, "Apologize", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Apologize.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(2, "Chi Can Em Hanh Phuc - Ho Quang Hieu", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Chi Can Em Hanh Phuc - Ho Quang Hieu.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(3, "Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Chuc-Em-Ngu-Ngon-Ngo-Kien-Huy-Thanh-Thao.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(4, "Cam On Nguoi Da Roi Xa Toi", "audio",
                "/storage/emulated/0/DataMediaKidRobot/Audio/Cam On Nguoi Da Roi Xa Toi - Pham Hong Phuoc.mp3",
                "Description for media object #1",
                true));
        media.add(new Media(5, "Kimetsu No Yaiba 1", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/5.mp4",
                "Description for media object #1",
                true));
        media.add(new Media(6, "Kimetsu No Yaiba 2", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/6.mp4",
                "Description for media object #2",
                true));
        media.add(new Media(7, "Kimetsu No Yaiba 3", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/7.mp4",
                "Description for media object #3",
                true));
        media.add(new Media(8, "Kimetsu No Yaiba 4", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/8.mp4",
                "Description for media object #4",
                true));

        media.add(new Media(9, "Kimetsu No Yaiba 5", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/9.mp4",
                "Description for media object #5",
                true,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
        media.add(new Media(10, "Kimetsu No Yaiba 6", "video",
                "/storage/emulated/0/DataMediaKidRobot/KimetsuNoYaiba/10.mp4",
                "Description for media object #6",
                true,
                "https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.png"
        ));
        KidRobotDatabase.getInstance(getApplicationContext())
                .mediaDao().insert(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
