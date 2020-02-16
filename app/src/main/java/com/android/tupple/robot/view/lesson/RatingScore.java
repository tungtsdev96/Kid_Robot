package com.android.tupple.robot.view.lesson;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * Created by tungts on 2020-02-16.
 */

public class RatingScore extends LinearLayout {

    public RatingScore(Context context) {
        super(context);
    }

    public RatingScore(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RatingScore(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RatingScore(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
