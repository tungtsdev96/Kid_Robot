package com.android.tupple.robot.view.englishbook;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

/**
 * Created by tungts on 2020-01-15.
 */

public class EnglishBookViewWrapperFactory {

    public static EnglishBookViewWrapperImpl newSchoolBookEnglishBookViewWrapper(FragmentManager fragmentManager, Bundle bundle) {
        return new EnglishBookViewWrapperImpl(fragmentManager, bundle);
    }

}
