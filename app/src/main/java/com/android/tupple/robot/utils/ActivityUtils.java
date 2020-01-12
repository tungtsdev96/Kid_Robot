package com.android.tupple.robot.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tungts on 2020-01-12.
 */

public class ActivityUtils {

    private final static String TAG = "ActivityUtils";

    public static void startActivty(Activity activity, Intent intent) {
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.d(TAG, "Can not open activity " + intent.getPackage() + " from " + activity.getClass().getName());
        }
    }

    public static void startActivityForResults(Activity activity, Intent intent, int requestCode) {
        try {
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            Log.d(TAG, "Can not open activity for result " + intent.getPackage() + " from " + activity.getClass().getName());
        }
    }

}
