package com.android.tupple.robot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.tupple.robot.R;
import com.android.tupple.robot.common.music.MultiPlayer;
import com.android.tupple.robot.data.remote.ApiFactory;
import com.android.tupple.robot.data.remote.questionanswer.QARequest;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.trigger.TriggerService;
import com.android.tupple.trigger.utils.TriggerConstant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

            handleQuestion(context, result);
        }
    }

    private void handleQuestion(Context context, String result) {
        QARequest request = new QARequest();
        request.setEntities(result);
        ApiFactory.getQAService().postTest(request).enqueue(new Callback<QAResponse>() {
            @Override
            public void onResponse(@NonNull Call<QAResponse> call, @NonNull Response<QAResponse> response) {
                if  (!response.isSuccessful() || response.body() == null) {
                    Log.d(TriggerService.TAG, " response answer success: " + response.isSuccessful());
                    return;
                }

                // TODO show dialog result answer
                QAResponse qaResponse = response.body();
                Toast.makeText(context, qaResponse.getResultText() + " " + qaResponse.getLinkAudio(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<QAResponse> call, Throwable t) {
                Log.d(TriggerService.TAG, "api err " + t.getLocalizedMessage());
            }
        });
    }

}
