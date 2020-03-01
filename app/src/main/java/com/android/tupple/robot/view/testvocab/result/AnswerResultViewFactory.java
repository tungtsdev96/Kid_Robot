package com.android.tupple.robot.view.testvocab.result;

import android.app.Activity;

import com.android.tupple.robot.domain.presenter.testvocab.result.AnswerResultView;

/**
 * Created by tungts on 2020-03-01.
 */

public class AnswerResultViewFactory {

    public static AnswerResultView newAnswerResultView(Activity activity) {
        return new AnswerResultViewImpl(activity);
    }

}
