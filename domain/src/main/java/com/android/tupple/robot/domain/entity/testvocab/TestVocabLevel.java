package com.android.tupple.robot.domain.entity.testvocab;

/**
 * Created by tungts on 2020-01-31.
 */

public enum TestVocabLevel {

    LEVEL1_1(0),
    LEVEL1_2(1),
    LEVEL2_1(2),
    LEVEL3_1(3);

    public int mValue;

    TestVocabLevel(int type) {
        this.mValue = type;
    }

}