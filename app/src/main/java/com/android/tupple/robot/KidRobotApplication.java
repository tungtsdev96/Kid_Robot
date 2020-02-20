package com.android.tupple.robot;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.tupple.robot.data.KidRobotDatabase;
import com.android.tupple.robot.data.entity.Topic;
import com.android.tupple.robot.data.entity.Vocabulary;
import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.log.CLogger;
import com.android.tupple.trigger.TriggerService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tungts on 2020-01-12.
 */

public class KidRobotApplication extends Application {

    private static KidRobotApplication sInstance;

    public KidRobotApplication() {
        super();
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initCLogger();
//        initTriggerService();

//        KidRobotDatabase.getInstance(sInstance);
//        initTopic();
        initVocab();
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

}
