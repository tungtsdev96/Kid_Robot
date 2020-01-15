package com.android.tupple.robot.domain.log;

import androidx.annotation.NonNull;

/**
 * Created by tung.ts on 1/15/2020.
 */

public class CLog {

    private static CLogger mLogger;

    private CLog() {
        throw new AssertionError();
    }

    public static void setLogger(@NonNull CLogger logger) {
        mLogger = logger;
    }

    public static void printSecD(String tag, String text) {
        if (mLogger == null) {
            return;
        }
        mLogger.printSecD(tag, text);
    }

    public static void printD(String tag, String text) {
        if (mLogger == null) {
            return;
        }
        mLogger.printD(tag, text);
    }

    public static void printSecE(String tag, String text) {
        if (mLogger == null) {
            return;
        }
        mLogger.printSecE(tag, text);
    }

    public static void printE(String tag, String text) {
        if (mLogger == null) {
            return;
        }
        mLogger.printE(tag, text);
    }

}
