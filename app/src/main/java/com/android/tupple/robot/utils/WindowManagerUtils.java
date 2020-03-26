package com.android.tupple.robot.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;

/**
 * Created by tungts on 2020-01-12.
 */

public class WindowManagerUtils {

    public static Window getWindowManager(Activity activity) {
        if (activity == null) {
            return null;
        }

        return activity.getWindow();
    }

    public static void setFullScreenMode(Activity activity) {
        Window window = getWindowManager(activity);

        if (window != null) {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
