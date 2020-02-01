package com.android.tupple.trigger;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import static com.android.tupple.trigger.TriggerService.ALLOW_WAKE_UP_WORD;
import static com.android.tupple.trigger.TriggerService.DIS_WAKE_UP_WORD;

/**
 * Created by tungts on 2020-02-01.
 */

public class TriggerHelper {

    public static void startTrigger(Activity activity) {
        try {
            activity.sendBroadcast(new Intent(ALLOW_WAKE_UP_WORD));
        } catch (Exception e) {
            Log.e(TriggerService.TAG, "Can't start trigger from " + activity);
        }
    }

    public static void stopTrigger(Activity activity) {
        try {
            activity.sendBroadcast(new Intent(DIS_WAKE_UP_WORD));
        } catch (Exception e) {
            Log.e(TriggerService.TAG, "Can't stop trigger from " + activity);
        }
    }

}
