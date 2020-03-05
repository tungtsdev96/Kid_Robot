package com.android.tupple.robot.utils;

/**
 * Created by tungts on 2020-02-21.
 */

public class StringUtils {

    public static String convertNumberToString(int value) {
        if (value < 10) {
            return "0" + value;
        } else {
            return String.valueOf(value);
        }
    }

}
