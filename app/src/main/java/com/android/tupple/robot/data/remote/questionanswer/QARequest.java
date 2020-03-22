package com.android.tupple.robot.data.remote.questionanswer;

/**
 * Created by tungts on 2020-02-01.
 */

public class QARequest {

    private String entities;

    public QARequest(String entities) {
        this.entities = entities;
    }

    public void setEntities(String entities) {
        this.entities = entities;
    }
}
