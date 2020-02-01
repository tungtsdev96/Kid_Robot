package com.android.tupple.robot;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.tupple.robot.domain.log.CLog;
import com.android.tupple.robot.domain.log.CLogger;
import com.android.tupple.trigger.TriggerService;

/**
 * Created by tungts on 2020-01-12.
 */

public class KidRobotApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initCLogger();
        initTriggerService();
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
