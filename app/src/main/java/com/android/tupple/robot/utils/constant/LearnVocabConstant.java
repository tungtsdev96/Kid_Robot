package com.android.tupple.robot.utils.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tungts on 2020-01-18.
 */

public final class LearnVocabConstant {

    @IntDef({LEARN_TYPE.TEXT, LEARN_TYPE.IMAGE, LEARN_TYPE.TEXT_AND_IMAGE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LEARN_TYPE {
        int TEXT = 0;
        int IMAGE = 1;
        int TEXT_AND_IMAGE = 2;
    }

    public interface TestVocab {
        String EXTRA_LIST_VOCAB_LEARNING = "extra_list_vocab_learning";
        String EXTRA_IS_LESSON = "extra_is_lesson";
    }

}
