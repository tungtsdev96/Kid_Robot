package com.android.tupple.robot.view.testvocab.progress;

import android.view.ViewGroup;

import com.android.tupple.robot.domain.presenter.testvocab.progress.TestProgressView;

/**
 * Created by tungts on 2020-03-09.
 */

public class TestProgressViewFactory {

    public static TestProgressView newTestProgressView(ViewGroup viewGroup) {
        return new TestProgressViewImpl(viewGroup);
    }

}
