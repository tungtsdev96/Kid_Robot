package com.android.tupple.robot.data.remote.questionanswer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungts on 2020-02-01.
 */

public class QAResponse {

    @SerializedName("audio_link")
    private String linkAudio;

    @SerializedName("text")
    private String resultText;

    public String getLinkAudio() {
        return linkAudio;
    }

    public void setLinkAudio(String linkAudio) {
        this.linkAudio = linkAudio;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
