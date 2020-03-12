package com.android.tupple.robot.data.remote;

import com.android.tupple.robot.data.remote.questionanswer.QARequest;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tungts on 2020-02-01.
 */

public interface QAService {

    @POST("/uploader")
    Call<QAResponse> postTest(@Body QARequest request);

}
