package com.android.tupple.robot.view.smartqa;

import android.app.Activity;

import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAView;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAViewFactory {

    public static SmartQAView<QAResponse> newSmartQAView(Activity activity) {
        return new SmartQAViewImpl(activity);
    }

}
