package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建者: Kyle
 * 创建时间: 2017/9/5.
 */

public class BezierCircleView extends View {

    public BezierCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint circlePaint;
    private Paint pointPaint;
    private Path path;
    // http://spencermortensen.com/articles/bezier-circle/
    private float blackMagic = 0.551915024494f;
    private float radius = 50f;
    private PointF[] points = new PointF[13];

    private void init() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        circlePaint = paint;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        pointPaint = paint;
        path = new Path();

        float m = radius * blackMagic;

        points[0] = new PointF(0, radius);
        points[1] = new PointF(m, radius);
        points[2] = new PointF(radius, m);
        points[3] = new PointF(radius,  0);
        points[4] = new PointF(radius, -m);
        points[5] = new PointF(m, -radius);
        points[6] = new PointF(0, -radius);
        points[7] = new PointF(-m, -radius);
        points[8] = new PointF(-radius, -m);
        points[9] = new PointF(-radius, 0);
        points[10] = new PointF(-radius, m);
        points[11] = new PointF(-m, radius);
        points[12] = points[0];


        //1. 改变右边三个点(p2, p3, p4), 移动x 坐标
        points[2].offset(100, 0);
        points[3].offset(100, 0);
        points[4].offset(100, 0);

        //2. 改变 上下 6个点(p0, p1, p11, p5, p6, p7)
        points[0].offset(50, 0);
        points[1].offset(50, 0);
        points[11].offset(50, 0);
        points[5].offset(50, 0);
        points[6].offset(50, 0);
        points[7].offset(50, 0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(300,300);
        pointPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(50, 50, radius, pointPaint);

        canvas.translate(radius, radius);
        path.reset();
        path.moveTo(points[0].x, points[0].y);
        for (int i = 0; i < 4; i++) {
            int index = i * 3;
            path.cubicTo(points[index + 1].x, points[index + 1].y,
                    points[index + 2].x, points[index + 2].y,
                    points[index + 3].x, points[index + 3].y);
        }

        canvas.drawPath(path, circlePaint);
    }
}
