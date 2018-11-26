package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用bsr曲线画圆
 * 创建者: Kyle
 * 创建时间: 2017/9/4.
 */

public class CustomView extends View {

    private static final String TAG = CustomView.class.getSimpleName();

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;
    private Paint paintPoint;
    private Path path;
    private PathMeasure pathMeasure;

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        path = new Path();
//        path.moveTo(0, 50);
//        path.quadTo(0, 0, 50, 0);
//
//        path.quadTo(100, 0, 100, 50);
//        path.quadTo(100, 100, 50, 100);
//        path.quadTo(0, 100, 0, 50);

        path.addCircle(100, 100, 100, Path.Direction.CCW);

        pathMeasure = new PathMeasure(path, false);

        paintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPoint.setColor(Color.BLUE);
        paintPoint.setStrokeWidth(10);

//        path.close();
    }

    float curr = 0f;
    float[] pos = new float[2];
    float[] tan = new float[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.save();
        canvas.translate(100, 100);
        if (curr < 1) {
            curr += 0.005f;
        } else {
            curr = 0f;
        }
        canvas.drawPath(path, mPaint);
        pathMeasure.getPosTan(pathMeasure.getLength() * curr, pos, tan);
//        Log.d(TAG, String.format("onDraw: x: %f, y: %f ", pos[0], pos[1]));

        canvas.drawPoint(pos[0], pos[1], paintPoint);

//        canvas.restore();
        invalidate();
    }
}
