package com.tqrg.physalia.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Ishan:
 * http://stackoverflow.com/questions/16650419/draw-in-canvas-by-finger-android
 */
public class DoodleCanvas  extends View {

    private Paint mPaint;
    private Path mPath;

    public DoodleCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(ScrollingActivity.interactive)
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                if(ScrollingActivity.interactive) {
                    mPath.moveTo(event.getX(), event.getY());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if(ScrollingActivity.interactive) {
                    mPath.lineTo(event.getX(), event.getY());
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                if(ScrollingActivity.interactive) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }

        return true;
    }
}