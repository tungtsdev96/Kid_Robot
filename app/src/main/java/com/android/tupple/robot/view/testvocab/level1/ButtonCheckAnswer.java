package com.android.tupple.robot.view.testvocab.level1;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by tungts on 2020-03-08.
 */

public class ButtonCheckAnswer extends AppCompatButton {

    enum State{CHECK, CONTINUE}

    private State mCurrentState = State.CHECK;

    public ButtonCheckAnswer(Context context) {
        super(context);
    }

    public ButtonCheckAnswer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonCheckAnswer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSate(boolean isCheck) {
        mCurrentState = isCheck ? State.CHECK : State.CONTINUE;
    }

    public boolean isCheck() {
        return mCurrentState == State.CHECK;
    }

}
