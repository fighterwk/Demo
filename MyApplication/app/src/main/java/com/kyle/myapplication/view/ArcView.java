package com.kyle.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/12
 */
public class ArcView extends View {
    private static String TAG = ArcView.class.getSimpleName();
    private Paint circlePaint;  // 圆背景边框画笔
    private Paint arcPaint;  // 圆弧画笔

    private float lumpDistance;  // 圆块距离
    private int lumpNum = 4;  // 圆块数量
    private float arcSweepAngle = 45f;  // 弧度
    private float arcAngle = 0;

    private int[] colors = new int[]{0x000000FF, Color.BLUE, 0x00000000};
    private float[] positions = new float[]{0, 1f / 8, 1f / 8};

    private int w = 400;
    private int h = 400;

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        this.circlePaint = paint;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        this.arcPaint = paint;

        this.lumpDistance = ((float) (this.w / 2)) / this.lumpNum;

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnim();
            }
        }, 500);
    }

    public void setArcAngle(float arcAngle) {
        this.arcAngle = arcAngle;
        invalidate();
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 362);
        valueAnimator.setDuration(3600);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                Log.e(TAG, "onAnimationUpdate: " + animatedValue);
                setArcAngle(animatedValue);
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);

        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();


        // 设置居中
        canvas.translate((width - w) / 2, (height - h) / 2);
        int r = w / 2;

//        for (int i = 0; i <= 360; i++) {
//            double degrees = Math.toDegrees(i);
//            float x = (float) (200 + 200 * Math.sin(degrees));
//            float y = (float) (200 + 200 * Math.cos(degrees));
//            canvas.drawPoint(x, y, arcPaint);
//        }

        // 绘制圆边框
        for (int i = 1; i <= lumpNum; i++) {
            canvas.drawCircle(r, r, lumpDistance * i, circlePaint);
        }

        RectF rect = new RectF(0, 0, w, h);
        // 设置圆形渐变(45°在圆中比例为1/8, 设置 position 渐变比例占比为1/8)
        SweepGradient shader =
                new SweepGradient(rect.centerX(), rect.centerY(),
                        colors, positions);
        arcPaint.setShader(shader);
        // 使用matrix 控制旋转
        Matrix matrix = new Matrix();
        matrix.postRotate(arcAngle, rect.centerX(), rect.centerY());
        canvas.concat(matrix);
//        canvas.drawArc(rect, 0, arcSweepAngle, true, arcPaint);
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, arcPaint);

    }
}
