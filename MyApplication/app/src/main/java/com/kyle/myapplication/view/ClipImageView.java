package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/11
 */
public class ClipImageView extends android.support.v7.widget.AppCompatImageView
        implements ScaleGestureDetector.OnScaleGestureListener {
    private static String TAG = ClipImageView.class.getSimpleName();
    private static final float NORMAL_SCALE = 1.0f;
    private static final float SMALL_SCALE = 2.0f;
    private static final float MAX_SCALE = 4.0f;

    private Paint borderPaint;  // 边框画笔
    private int horizontalInterval = 20; // 间隔
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;
    private Matrix mMatrix = new Matrix();

    public ClipImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setScaleType(ScaleType.MATRIX);
        mMatrix.setScale(NORMAL_SCALE, NORMAL_SCALE);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setColor(0xaa000000);
        borderPaint.setStyle(Paint.Style.FILL);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.d(TAG, "onDoubleTap: ");
                if (getDrawable() == null){
                    return true;
                }

                if (getMatrixScale() < SMALL_SCALE){
                    mMatrix.setScale(SMALL_SCALE, SMALL_SCALE);
                }else{
                    mMatrix.setScale(NORMAL_SCALE, NORMAL_SCALE);
                }
                setImageMatrix(mMatrix);
                return true;
            }


        });
        scaleGestureDetector = new ScaleGestureDetector(context, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }

        scaleGestureDetector.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    public  float getMatrixScale() {
        float[] values = new float[9];
        mMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (getDrawable() == null) {
            return true;
        }
        float scaleFactor = detector.getScaleFactor();
        mMatrix.postScale(scaleFactor, scaleFactor);
        setImageMatrix(mMatrix);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int innerWidth = width - horizontalInterval * 2;
        Rect rect = new Rect(0, 0, innerWidth, innerWidth);
        int outInterval = (height - rect.height()) / 2; // 外部间隔

        canvas.drawRect(new Rect(0, 0, width, outInterval), borderPaint);
        canvas.drawRect(new Rect(0, height - outInterval, width, height), borderPaint);

        canvas.drawRect(new Rect(0, outInterval, horizontalInterval, height - outInterval), borderPaint);
        canvas.drawRect(new Rect(width - horizontalInterval, outInterval, width, height - outInterval), borderPaint);

        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(2);
        borderPaint.setColor(Color.RED);

        rect.offset(horizontalInterval, outInterval);
        canvas.drawRect(rect, borderPaint);

    }
}
