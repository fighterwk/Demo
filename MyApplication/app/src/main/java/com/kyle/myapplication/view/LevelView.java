package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LevelView extends View {

    private int width;
    private int height;
    private Paint mPaint;
    private Bitmap rectBitmap;
    private Bitmap boundBitmap;


    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#ff0000"));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = this.getPaddingLeft() + this.getPaddingRight();
        int viewHeight = this.getPaddingTop() + this.getPaddingBottom();

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(viewWidth, widthSize);
        } else {
            //Be whatever you want
            width = viewWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(viewHeight, heightSize);
        } else {
            //Be whatever you want
            height = viewHeight;
        }

        setMeasuredDimension(width, height);
        makeRect();
        makeBound();
        change();
    }


    private int pathBWidth = 100;

    private void makeBound() {
        boundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(boundBitmap);
        c.drawColor(Color.TRANSPARENT);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        p.setStyle(Paint.Style.STROKE);//设置为画边框

        Path pathA = new Path();
        RectF rectA1 = new RectF(0, 0, 100, 100);//圆弧的圆心在对角线中间，即：50,50
        pathA.addArc(rectA1, 90, 180);
        pathA.lineTo(200, 0);
        RectF rectA2 = new RectF(150, 0, 250, 100);//圆弧的圆心在对角线中间，即：200,50
        pathA.addArc(rectA2, 270, 180);
        pathA.lineTo(50, 100);

        c.drawPath(pathA, p);
    }

    private void makeRect() {
        rectBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(rectBitmap);
        c.drawColor(Color.TRANSPARENT);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        p.setStyle(Paint.Style.FILL);//设置为画边框

        Path pathA = new Path();
        RectF rectA1 = new RectF(0, 0, 100, 100);//圆弧的圆心在对角线中间，即：50,50
        pathA.addArc(rectA1, 90, 180);
        pathA.lineTo(200, 0);
        RectF rectA2 = new RectF(150, 0, 250, 100);//圆弧的圆心在对角线中间，即：200,50
        pathA.addArc(rectA2, 270, 180);
        pathA.lineTo(50, 100);

        Path pathB = new Path();
        RectF rectB = new RectF(0, 0, pathBWidth, 100);
        pathB.addRect(rectB, Path.Direction.CCW);
        pathB.close();

//        pathA.op(pathB, Path.Op.INTERSECT);
        c.drawPath(pathB, p);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawBitmap(boundBitmap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(rectBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);
    }

    private void change() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    SystemClock.sleep(10);
//                    pathBWidth += 1;
//                    if (pathBWidth > 250) {
//                        pathBWidth = 0;
//                    }
//                    postInvalidate();
//                    makeRect();
//                }
//            }
//        }).start();
    }
}