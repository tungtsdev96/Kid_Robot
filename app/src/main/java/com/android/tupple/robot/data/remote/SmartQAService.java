package com.android.tupple.robot.data.remote;

import com.android.tupple.robot.data.remote.questionanswer.QARequest;
import com.android.tupple.robot.data.remote.questionanswer.QAResponse;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by tungts on 2020-02-01.
 */

public interface SmartQAService {

    @POST("/uploader/audio")
    Single<QAResponse> postTest(@Body QARequest request);

    @Multipart
    @POST("/uploader/audio")
    Single<QAResponse> postAudio(@Part MultipartBody.Part audio);

}
