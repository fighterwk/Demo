package com.kyle.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;


/**
 * @Description描述:
 * @Author作者: hx
 * @Date日期: 2017/3/1
 */

public class DoughnutView extends View {
    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 400;
    //圆环颜色
    private int[] doughnutColors = new int[]{Color.parseColor("#0182eb")
            , Color.parseColor("#028DEC")
            , Color.parseColor("#0CD5EF")
            , Color.parseColor("#0CD5EF")
            , Color.parseColor("#028DEC")
            , Color.parseColor("#0182eb")};

    private int width;
    private int height;
    private float currentValue = 0f;
    private Paint paint = new Paint();
    private int percentTextColor = Color.parseColor("#333333");
    private int percentTextSize = 30;
    private int textColor = Color.parseColor("#666666");
    private int textSize = 14;
    /**
     * Pointer position in terms of X and Y coordinates.
     */
    protected float[] mPointerPositionXY = new float[2];
    private int doughnutWidth = 8;
    private String content = "";
    private Context context;

    public DoughnutView(Context context) {
        super(context);
        this.context = context;
        doughnutWidth = dip2px(context, doughnutWidth);
    }

    public DoughnutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        doughnutWidth = dip2px(context, doughnutWidth);
        postDelayed(new Runnable() {
            @Override
            public void run() {
              setValue(1);
            }
        }, 300);
    }

    public DoughnutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        doughnutWidth = dip2px(context, doughnutWidth);
    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }


    public void setValue(float value) {
        value = 360 * value;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(currentValue, value);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return 1 - (1 - v) * (1 - v) * (1 - v);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();
        //画背景白色圆环
        initPaint();
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#E5E5E5"));
        RectF rectF = new RectF((width > height ? Math.abs(width - height) / 2 : 0) + doughnutWidth / 2 + 10,
                (height > width ? Math.abs(height - width) / 2 : 0) + doughnutWidth / 2 + 10,
                width - (width > height ? Math.abs(width - height) / 2 : 0) - doughnutWidth / 2 - 10,
                height - (height > width ? Math.abs(height - width) / 2 : 0) - doughnutWidth / 2 - 10);

        canvas.drawArc(rectF, 0, 360, false, paint);

        //画彩色圆环
        initPaint();
        canvas.rotate(-90, width / 2, height / 2);
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        if (doughnutColors.length > 1) {
            paint.setShader(new SweepGradient(width / 2, height / 2, doughnutColors, null));
        } else {
            paint.setColor(doughnutColors[0]);
        }
        Path mCircleProgressPath = new Path();
        mCircleProgressPath.addArc(rectF, 0, currentValue);
        canvas.drawPath(mCircleProgressPath, paint);
        calculatePointerXYPosition(mCircleProgressPath);

        //画中间数值的背景
        initPaint();
        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.WHITE);
//        canvas.drawCircle(width / 2, height / 2, fontSize * 2, paint);

        //画中间数值
        canvas.rotate(90, width / 2, height / 2);
        initPaint();
        paint.setColor(percentTextColor);
        paint.setTextSize(sp2px(context, percentTextSize));
        paint.setTextAlign(Paint.Align.CENTER);
        float baseLine = height / 2 - (paint.getFontMetrics().descent + paint.getFontMetrics().ascent) / 2;
        canvas.drawText((int) (currentValue / 360f * 100) + "%", width / 2, baseLine, paint);
        paint.setColor(textColor);
        paint.setTextSize(sp2px(context, textSize));
        canvas.drawText(content, width / 2, baseLine + 80, paint);

        //画圆点
        if (currentValue > 0) {
            paint.setColor(getCurrentColor(currentValue / 360f, doughnutColors));
            canvas.rotate(-90, width / 2, height / 2);
            canvas.drawCircle(mPointerPositionXY[0], mPointerPositionXY[1], doughnutWidth / 2 + 5, paint);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(mPointerPositionXY[0], mPointerPositionXY[1], doughnutWidth / 2 - 5, paint);
        }
    }

    protected void calculatePointerXYPosition(Path p) {
        PathMeasure pm = new PathMeasure(p, false);
        boolean returnValue = pm.getPosTan(pm.getLength(), mPointerPositionXY, null);
        if (!returnValue) {
            pm = new PathMeasure(p, false);
            returnValue = pm.getPosTan(0, mPointerPositionXY, null);
        }
    }

    /**
     * 当布局为wrap_content时设置默认长宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = View.MeasureSpec.getMode(origin);
        int specSize = View.MeasureSpec.getSize(origin);
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 颜色渐变算法
     * 获取某个百分比下的渐变颜色值
     *
     * @param percent
     * @param colors
     * @return
     */
    private int getCurrentColor(float percent, int[] colors) {
        float[][] f = new float[colors.length][3];
        for (int i = 0; i < colors.length; i++) {
            f[i][0] = (colors[i] & 0xff0000) >> 16;
            f[i][1] = (colors[i] & 0x00ff00) >> 8;
            f[i][2] = (colors[i] & 0x0000ff);
        }
        float[] result = new float[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < f.length; j++) {
                if (f.length == 1 || percent == j / (f.length - 1f)) {
                    result = f[j];
                } else {
                    if (percent > j / (f.length - 1f) && percent < (j + 1f) / (f.length - 1)) {
                        result[i] = f[j][i] - (f[j][i] - f[j + 1][i]) * (percent - j / (f.length - 1f)) * (f.length - 1f);
                    }
                }
            }
        }
        return Color.rgb((int) result[0], (int) result[1], (int) result[2]);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    static int dip2px(Context context, float pxValue){
        final float density = context.getResources().getDisplayMetrics().density;
        return (int)(density * pxValue +0.5f);
    }
}
