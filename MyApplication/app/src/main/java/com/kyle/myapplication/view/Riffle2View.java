package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @Description描述:
 * @Author作者: liuyixin
 * @Date日期: 2017/11/30
 */
public class Riffle2View extends View {

    private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int radius = 0;  // 波纹半径
    private int minRadius;   // 最小半径


    private Path innerCirclePath = new Path();
    private Path outCirclePath = new Path();

    // 内部圆绘制偏移
    private int offsetX = 0;
    private int offsetY = 0;
    private boolean initOffset = false;

    // 绘制外部圆的中心点
    private int centerX = 0;
    private int centerY = 0;

    // 控件宽高
    private int mWidth = 0;
    private int mHeight = 0;


    public Riffle2View(Context context) {
        this(context, null);
    }

    public Riffle2View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Riffle2View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        minRadius = dip2px(getContext(), 130);
        mBgPaint.setColor(Color.WHITE);
        setLayerType(LAYER_TYPE_HARDWARE, null);
    }

    // 绘制内部圆
    private void createInnerCircleBm() {
        initOffset = false;

        innerCirclePath.setFillType(Path.FillType.INVERSE_WINDING);
        innerCirclePath.addCircle(centerX, centerY, minRadius, Path.Direction.CW);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        centerX = mWidth / 2;
        centerY = mHeight / 2;

        createInnerCircleBm();
    }

    /**
     * 设置绘制圆的半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
        postInvalidate();
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
        createInnerCircleBm();
    }

    public int getMinRadius() {
        return minRadius;
    }

    /**
     * 重置到初始化
     */
    public void reset() {
        this.radius = 0;
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
    }

    // 绘制波纹
    private void drawWave(Canvas canvas) {
        canvas.save();

        canvas.clipPath(innerCirclePath);
        canvas.drawCircle(centerX, centerY, radius, mBgPaint);

        canvas.restore();

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
