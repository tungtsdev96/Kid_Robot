package com.android.tupple.robot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.tupple.trigger.TriggerService;
import com.android.tupple.trigger.utils.TriggerConstant;

/**
 * Created by tungts on 2020-02-01.
 */

public class RecordingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.d(TriggerService.TAG, action);

        if (TextUtils.isEmpty(action)) {
            return;
        }

        if (TriggerConstant.ACTION_RECOGNIZE_DONE.equals(action)) {
            String result = intent.getStringExtra(TriggerConstant.EXTRA_RECOGNIZE_DONE);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
    }

}
