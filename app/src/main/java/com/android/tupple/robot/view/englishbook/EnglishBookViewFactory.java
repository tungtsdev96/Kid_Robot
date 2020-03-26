package com.android.tupple.robot.view.englishbook;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookViewFactory {

    public static EnglishBookViewImpl newEnglishBookView(Activity activity, Bundle bundle) {
        return new EnglishBookViewImpl(activity, bundle);
    }

}
