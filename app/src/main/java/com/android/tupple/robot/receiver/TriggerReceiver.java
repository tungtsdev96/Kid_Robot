package com.android.tupple.robot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.tupple.robot.activity.RecordingActivity;

/**
 * Created by tungts on 2020-01-19.
 */

public class TriggerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action == null) {
            return;
        }

        if ("tungts".equalsIgnoreCase(action)) {
            String output = intent.getStringExtra("text");
            Toast.makeText(context, "" + output, Toast.LENGTH_SHORT).show();

            startRecordingActivity(context);
        }
    }

    private void startRecordingActivity(Context context) {
        Intent i = new Intent(context, RecordingActivity.class);
        context.startActivity(i);
    }

}
