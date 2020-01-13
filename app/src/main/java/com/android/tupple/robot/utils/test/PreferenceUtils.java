//package com.android.tupple.robot.utils;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import androidx.preference.PreferenceManager;
//
//import com.samsung.android.libcalendar.common.R;
//import com.samsung.android.libcalendar.common.constant.PreferenceConstants;
//
//public class PreferenceUtils {
//
//    private static final String TAG = "PreferenceUtils";
//
//    private static final int sDefaultValueId;
//
//    static {
//        if (Feature.REGIONAL_USA_VZW) {
//            sDefaultValueId = R.xml.preferences_default_value_vzw;
//        } else {
//            sDefaultValueId = R.xml.preferences_default_value;
//        }
//    }
//
//    /**
//     * Set the default shared preferences in the proper context
//     */
//    public static void setDefaultValues(Context context, boolean readAgain) {
//        PreferenceManager.setDefaultValues(context, PreferenceConstants.SHARED_PREFS_NAME, Context.MODE_PRIVATE,
//                sDefaultValueId, readAgain);
//    }
//
//    /**
//     * Return a properly configured SharedPreferences instance
//     */
//    public static SharedPreferences getSharedPreferences(Context context) {
//        return context.getSharedPreferences(PreferenceConstants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
//    }
//
//    public static String getSharedPreference(Context context, String key, String defaultValue) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        return prefs.getString(key, defaultValue);
//    }
//
//    public static int getSharedPreference(Context context, String key, int defaultValue) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        return prefs.getInt(key, defaultValue);
//    }
//
//    public static float getSharedPreference(Context context, String key, float defaultValue) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        return prefs.getFloat(key, defaultValue);
//    }
//
//    public static boolean getSharedPreference(Context context, String key, boolean defaultValue) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        return prefs.getBoolean(key, defaultValue);
//    }
//
//    public static long getSharedPreference(Context context, String key, long defaultValue) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        return prefs.getLong(key, defaultValue);
//    }
//
//    public static void setSharedPreference(Context context, String key, String value) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().putString(key, value).apply();
//    }
//
//    public static void setSharedPreference(Context context, String key, boolean value) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().putBoolean(key, value).apply();
//    }
//
//    public static void setSharedPreference(Context context, String key, int value) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().putInt(key, value).apply();
//    }
//
//    public static void setSharedPreference(Context context, String key, float value) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().putFloat(key, value).apply();
//    }
//
//    public static void setSharedPreference(Context context, String key, long value) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().putLong(key, value).apply();
//    }
//
//    public static void clearSharedPreference(Context context) {
//        SharedPreferences prefs = getSharedPreferences(context);
//        prefs.edit().clear().apply();
//    }
//}
