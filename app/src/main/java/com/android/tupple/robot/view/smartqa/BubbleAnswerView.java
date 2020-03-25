package com.android.tupple.robot.view.smartqa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.tupple.robot.R;

/**
 * Created by tungts on 3/22/20.
 */

public class BubbleAnswerView extends View {

    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Path mArrowPath;
    private RectF mChatRect;

    public BubbleAnswerView(Context context) {
        super(context);
    }

    public BubbleAnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BubbleAnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mArrowPath = new Path();
        mChatRect = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getContext().getResources().getColor(R.color.bg_dialog_smart_answer));
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
        float arrowSize = getResources().getDimension(R.dimen.dialog_arrow_size);
        drawArrow(canvas, arrowSize);
        mChatRect.set(arrowSize, 0, mWidth, mHeight);
        canvas.drawRoundRect(mChatRect, 90 , 90, mPaint);
    }

    private void drawArrow(Canvas canvas, float arrowSize) {
        mArrowPath.reset();
        mArrowPath.moveTo(0, mHeight / 4.0f);
        mArrowPath.lineTo(arrowSize, mHeight / 4.0f);
        mArrowPath.moveTo(0, mHeight / 4.0f);
        mArrowPath.lineTo(arrowSize, mHeight / 2.0f);
        mArrowPath.lineTo(arrowSize, mHeight / 4.0f);
        mArrowPath.close();
        canvas.drawPath(mArrowPath, mPaint);
    }
}
