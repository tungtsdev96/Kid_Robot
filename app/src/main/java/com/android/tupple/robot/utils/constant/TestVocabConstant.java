package com.android.tupple.robot.utils.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tungts on 2020-02-06.
 */

public final class TestVocabConstant {

    @IntDef({ANSWER_TYPE.TEXT, ANSWER_TYPE.IMAGE, ANSWER_TYPE.TEXT_AND_IMAGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ANSWER_TYPE {
        int TEXT = 0;
        int IMAGE = 1;
        int TEXT_AND_IMAGE = 2;
    }

}
