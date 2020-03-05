package com.android.tupple.robot.domain.presenter.testvocab.level3;

/**
 * Created by tungts on 2020-02-29.
 */

public enum RecordState {

    WAITING(-1),
    PREPARING(0),
    NORMAL(1),
    RECORDING(2);

    private int mValue;

    RecordState(int value) {
        this.mValue = value;
    }
}
