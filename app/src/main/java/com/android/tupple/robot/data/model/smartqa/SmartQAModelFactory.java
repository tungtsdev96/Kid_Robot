package com.android.tupple.robot.data.model.smartqa;

import android.content.Context;

import com.android.tupple.robot.data.remote.questionanswer.QAResponse;
import com.android.tupple.robot.domain.presenter.smartqa.SmartQAModel;

/**
 * Created by tungts on 3/22/20.
 */

public class SmartQAModelFactory {

    public static SmartQAModel<QAResponse> newSmartQAModel(Context context) {
        return new SmartQAModelImpl(context);
    }

}
