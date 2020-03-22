package com.android.tupple.robot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.android.tupple.robot.activity.SmartQAActivity;
import com.android.tupple.trigger.TriggerService;
import com.android.tupple.trigger.utils.TriggerConstant;

/**
 * Created by tungts on 2020-02-01.
 */

public class RecordingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.d(TriggerService.TAG, "RecordingReceiver " + action);

        if (TextUtils.isEmpty(action)) {
            return;
        }

        if (TriggerConstant.ACTION_RECOGNIZE_DONE.equals(action)) {
            String result = intent.getStringExtra(TriggerConstant.EXTRA_RECOGNIZE_DONE);
            handleQuestion(context, result);
        }
    }

    private void handleQuestion(Context context, String result) {
        Intent intent = new Intent(context, SmartQAActivity.class);
        intent.putExtra("question", result);
        context.startActivity(intent);
    }

}
