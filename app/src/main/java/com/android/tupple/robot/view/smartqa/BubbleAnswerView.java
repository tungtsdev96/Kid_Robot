package com.android.tupple.robot.view.smartqa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.tupple.robot.R;

/**
 * Created by tungts on 3/22/20.
 */

public class BubbleAnswerView extends AppCompatTextView {

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Path mDialogPath;

    public BubbleAnswerView(Context context) {
        super(context);
    }

    public BubbleAnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTextSize();
        initPaint();
    }

    public BubbleAnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mDialogPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getContext().getResources().getColor(R.color.bg_dialog_smart_answer));
    }

    private void initTextSize() {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        setGravity(TEXT_ALIGNMENT_CENTER);
        setTextColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDialogPath.reset();
        canvas.drawRect(-mHeight / 2.0f, -mWidth / 2.0f, mWidth, mHeight, mPaint);
    }
}
