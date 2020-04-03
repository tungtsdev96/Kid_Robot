package com.android.tupple.robot.common.customview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.android.tupple.robot.R;
import com.android.tupple.robot.utils.GlideUtils;

/**
 * Created by tungts on 4/3/20.
 */

public class ImageBackground extends AppCompatImageView {

    public ImageBackground(Context context) {
        super(context);
    }

    public ImageBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        GlideUtils.loadImageFromDrawable(context, R.drawable.kid_background2, this, "background");
    }

    public ImageBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
