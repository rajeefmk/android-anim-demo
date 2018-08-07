package com.rajeefmk.androidanimdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Rajeef on 7/8/18
 */
public class CustomCircleView extends View {

    private int circleCol, labelCol;

    private String circleText;

    private Paint circlePaint;

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        circlePaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCircle, 0, 0);
        try {
            circleText = a.getString(R.styleable.MyCircle_circleLabel);
            circleCol = a.getInteger(R.styleable.MyCircle_circleColor, 0);
            labelCol = a.getInteger(R.styleable.MyCircle_labelColor, 0);

        } finally {
            a.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = this.getMeasuredWidth() / 2;
        int viewHeight = this.getMeasuredHeight() / 2;
        int radius = 0;

        if (viewWidth > viewHeight)
            radius = viewHeight - 10;
        else
            radius = viewWidth - 10;
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleCol);

        canvas.drawCircle(viewWidth, viewHeight, radius, circlePaint);

        //Text
        circlePaint.setColor(labelCol);
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);

        canvas.drawText(circleText, viewWidth, viewHeight, circlePaint);
    }
}
