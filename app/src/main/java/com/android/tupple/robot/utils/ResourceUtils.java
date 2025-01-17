package com.android.tupple.robot.utils;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;

/**
 * Created by tungts on 2020-01-13.
 */

public class ResourceUtils {

    @DrawableRes
    public static int convertNameToDrawableRes(Context context, String name) {
        Resources res = context.getResources();
        return res.getIdentifier(name,"drawable",context.getPackageName());
    }

    @RawRes
    public static int convertNameToRawRes(Context context, String name) {
        Resources res = context.getResources();
        return res.getIdentifier(name,"raw", context.getPackageName());
    }

}
