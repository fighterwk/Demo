package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


public class DynamicWave extends View {
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 40;
    // y = Asin(wx+b)+h
    private static final int OFFSET_Y = 0;
    // 移动速度
    private static final int TRANSLATE_X_SPEED = 3;
    private float mCycleFactorW;

    private int mTotalWidth, mTotalHeight;
    private float[] mYPositions1;
    private float[] mYPositions2;
    private float[] mYPositions3;
    private float[] mResetOneYPositions;
    private float[] mResetTwoYPositions;
    private float[] mResetThreeYPositions;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOffsetSpeedThree;
    private int mXOneOffset;
    private int mXTwoOffset;
    private int mXThreeOffset;

    private Paint mWavePaint;
    private DrawFilter mDrawFilter;

    public DynamicWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 将dp转化为px，用于控制不同分辨率上移动速度基本一致
        mXOffsetSpeedOne = dipToPx(context, TRANSLATE_X_SPEED);
        mXOffsetSpeedTwo = dipToPx(context, 5);
        mXOffsetSpeedThree = dipToPx(context,4);

        // 初始绘制波纹的画笔
        mWavePaint = new Paint();
        // 去除画笔锯齿
        mWavePaint.setAntiAlias(true);
        // 设置风格为实线
        mWavePaint.setStyle(Paint.Style.STROKE);
        // 设置画笔颜色
        mWavePaint.setAlpha(100);
        mWavePaint.setColor(Color.WHITE);
        mWavePaint.setStrokeWidth(2);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 从canvas层面去除绘制时锯齿
        canvas.setDrawFilter(mDrawFilter);
        resetPositonY();
        for (int i = 0; i < mTotalWidth; i++) {
            mWavePaint.setColor(Color.parseColor("#23ffdd"));
            canvas.drawPoint(i,mTotalHeight-mResetOneYPositions[i]-500,mWavePaint);
            mWavePaint.setColor(Color.parseColor("#7cff92"));
            canvas.drawPoint(i,mTotalHeight-mResetTwoYPositions[i]-500,mWavePaint);
            mWavePaint.setColor(Color.parseColor("#ffee5a"));
            canvas.drawPoint(i,mTotalHeight-mResetThreeYPositions[i]-500,mWavePaint);
        }

        // 改变两条波纹的移动点
        mXOneOffset += mXOffsetSpeedOne;
        mXTwoOffset += mXOffsetSpeedTwo;
        mXThreeOffset += mXOffsetSpeedThree;

        // 如果已经移动到结尾处，则重头记录
        if (mXOneOffset >= mTotalWidth) {
            mXOneOffset = 0;
        }
        if (mXTwoOffset > mTotalWidth) {
            mXTwoOffset = 0;
        }
        if (mXThreeOffset > mTotalWidth) {
            mXThreeOffset = 0;
        }

        // 引发view重绘，一般可以考虑延迟20-30ms重绘，空出时间片
        postInvalidate();
    }

    private void resetPositonY() {
        // mXOneOffset代表当前第一条水波纹要移动的距离
        int yOneInterval = mYPositions1.length - mXOneOffset;
        // 使用System.arraycopy方式重新填充第一条波纹的数据
        System.arraycopy(mYPositions1, mXOneOffset, mResetOneYPositions, 0, yOneInterval);
        System.arraycopy(mYPositions1, 0, mResetOneYPositions, yOneInterval, mXOneOffset);

        int yTwoInterval = mYPositions2.length - mXTwoOffset;
        System.arraycopy(mYPositions2, mXTwoOffset, mResetTwoYPositions, 0,
                yTwoInterval);
        System.arraycopy(mYPositions2, 0, mResetTwoYPositions, yTwoInterval, mXTwoOffset);

        int yThreeInterval = mYPositions3.length - mXThreeOffset;
        System.arraycopy(mYPositions3, mXThreeOffset, mResetThreeYPositions, 0,
                yThreeInterval);
        System.arraycopy(mYPositions3, 0, mResetThreeYPositions, yThreeInterval, mXThreeOffset);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 记录下view的宽高
        mTotalWidth = w;
        mTotalHeight = h;
        // 用于保存原始波纹的y值
        mYPositions1 = new float[mTotalWidth];
        mYPositions2 = new float[mTotalWidth];
        mYPositions3 = new float[mTotalWidth];
        // 用于保存波纹一的y值
        mResetOneYPositions = new float[mTotalWidth];
        // 用于保存波纹二的y值
        mResetTwoYPositions = new float[mTotalWidth];
        // 用于保存波纹三的y值
        mResetThreeYPositions = new float[mTotalWidth];

        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / mTotalWidth);

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < mTotalWidth; i++) {
            mYPositions1[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
            mYPositions2[i] = (float) (50 * Math.sin(mCycleFactorW * i+30) + 10);
            mYPositions3[i] = (float) (60 * Math.sin(mCycleFactorW * i+40) + 20);
        }
    }

    public int dipToPx(Context context, int dip) {
        return (int) (dip * getScreenDensity(context) + 0.5f);
    }

    public float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                    .getMetrics(dm);
            return dm.density;
        } catch (Exception e) {
            return DisplayMetrics.DENSITY_DEFAULT;
        }
    }

}
