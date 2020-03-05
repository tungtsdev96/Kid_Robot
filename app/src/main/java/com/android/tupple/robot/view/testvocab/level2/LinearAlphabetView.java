package com.android.tupple.robot.view.testvocab.level2;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.tupple.robot.R;

/**
 * Created by tungts on 2020-02-10.
 */

public class LinearAlphabetView extends LinearLayout {

    private Context mContext;

    public LinearAlphabetView(Context context) {
        super(context);
        this.mContext = context;
    }

    public LinearAlphabetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public LinearAlphabetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LinearAlphabetView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
    }

    public void generateView(char[] text) {
        for (int i = 0; i < text.length; i++) {
            char x = text[i];
            AlphabetView view = new AlphabetView(mContext);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMarginStart(mContext.getResources().getDimensionPixelOffset(R.dimen.alphabet_view_margin_left));
            view.setLayoutParams(layoutParams);
            view.setContent(x);

            this.addView(view);
        }
    }

}
