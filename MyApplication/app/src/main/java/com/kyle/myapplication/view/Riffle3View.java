package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @Description描述:
 * @Author作者: liuyixin
 * @Date日期: 2017/11/30
 */
public class Riffle3View extends View {

    private Paint mPint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int radius = 0;  // 波纹半径
    private int minRadius;   // 最小半径

    private Bitmap innerCircleBm;  // 内部圆
    private Bitmap outCircleBm;  // 外部圆
    private Canvas outCircleCanvas;  // 外部圆画布

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


    public Riffle3View(Context context) {
        this(context, null);
    }

    public Riffle3View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Riffle3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
//        minRadius = CommonUtils.dip2px(context, 130);
        mBgPaint.setColor(Color.WHITE);
        mPint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        drawInnerCircleBm();
    }

    // 绘制内部圆
    private void drawInnerCircleBm() {
        initOffset = false;
        if (innerCircleBm != null && !innerCircleBm.isRecycled()) {
            innerCircleBm.recycle();
            innerCircleBm = null;
        }
        // 内部圆
        innerCircleBm = Bitmap.createBitmap(minRadius * 2, minRadius * 2,
                Bitmap.Config.ARGB_4444);

        Canvas canvas = new Canvas(innerCircleBm);
        canvas.drawCircle(minRadius, minRadius, minRadius,
                mBgPaint);
    }

    // 创建外部圆
    private void createOutCircleBm() {
        if (outCircleBm != null) {
            return;
        }
        outCircleBm = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_4444);
        Canvas bmCanvas = new Canvas(outCircleBm);
        this.outCircleCanvas = bmCanvas;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        centerX = mWidth / 2;
        centerY = mHeight / 2;

        createOutCircleBm();
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
        drawInnerCircleBm();
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
        int save = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.ALL_SAVE_FLAG);

        // 有点消耗cpu
        if (!initOffset) {
            offsetX = (getWidth() - innerCircleBm.getWidth()) / 2;
            offsetY = (getHeight() - innerCircleBm.getHeight()) / 2;
            initOffset = true;
        }

        if (!innerCircleBm.isRecycled()) {
            canvas.drawBitmap(innerCircleBm, offsetX,
                    offsetY, null);
        }

        // 清空外部圆画布
        this.outCircleCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        // 绘制外部圆
        this.outCircleCanvas.drawCircle(centerX,
                centerY, radius, mBgPaint);
        canvas.drawBitmap(outCircleBm, 0, 0, mPint);

        canvas.restoreToCount(save);
    }
}
