package com.android.tupple.robot.domain.presenter.testvocab.level3;

/**
 * Created by tungts on 2020-02-29.
 */

public enum ResultState {

    INVALID(-1),
    NOT_GOOD(0),
    GOOD(1),
    VERY_GOOD(2),
    EXCELLENT(3);

    public int value;

    ResultState(int value) {
        this.value = value;
    }
}
