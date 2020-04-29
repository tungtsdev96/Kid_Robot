package com.android.tupple.robot.data.remote.questionanswer;

/**
 * Created by tungts on 2020-02-01.
 */

public class QARequest {

    private String entities;

    private short[] buffersAudio;

    public QARequest(String entities) {
        this.entities = entities;
    }

    public QARequest(short[] buffers) {
        buffersAudio = buffers;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }
}
