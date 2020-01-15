package com.android.tupple.robot.model.english;

import android.content.Context;

import com.android.tupple.robot.commondata.SchoolBook;
import com.android.tupple.robot.domain.presenter.englishbook.EnglishBookModel;

/**
 * Created by tungts on 2020-01-13.
 */

public class EnglishBookModelImpl implements EnglishBookModel<SchoolBook> {

    private Context mContext;

    public EnglishBookModelImpl(Context mContext) {
        this.mContext = mContext;
    }
}
