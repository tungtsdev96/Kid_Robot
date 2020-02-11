package com.android.tupple.robot.view.testvocab.level2;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.android.tupple.robot.R;

/**
 * Created by tungts on 2020-02-10.
 */

public class AlphabetView extends AppCompatTextView {

    public AlphabetView(Context context) {
        super(context);
        init(context);
    }

    public AlphabetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AlphabetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Typeface typeface = ResourcesCompat.getFont(context, R.font.boosternextfy_bold);
        setTypeface(typeface);
        setBackgroundResource(R.drawable.bg_alphabet_disable);

        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        setPadding(
                context.getResources().getDimensionPixelOffset(R.dimen.alphabet_view_padding),
                context.getResources().getDimensionPixelOffset(R.dimen.alphabet_view_padding),
                context.getResources().getDimensionPixelOffset(R.dimen.alphabet_view_padding),
                context.getResources().getDimensionPixelOffset(R.dimen.alphabet_view_padding)
        );
    }

    public void setContent(char content) {
        this.setText(content);
        if (content >= 'a' && content <= 'z' || content >= 'A' && content <= 'Z') {
            setBackgroundResource(R.drawable.bg_alphabet);
        } if (content == '_') {
            setBackgroundResource(R.drawable.bg_text_answer_level_3);
        } else {
            setBackgroundResource(R.drawable.bg_alphabet_disable);
        }
    }

}
