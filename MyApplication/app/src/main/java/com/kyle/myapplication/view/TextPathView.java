package com.kyle.myapplication.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/5/25
 */
public class TextPathView extends View {

    private final String str = "Hello Kyle";
    // 画笔信息
    private Paint mPaint;
    // 绘制点画笔
    private Paint mPosPaint;
    // 文本路劲信息
    private Path mSourcePath;
    // 绘制文本路劲(dst = destination)
    private Path mDstPath;
    // 获取文本的路劲
    private PathMeasure pathMeasure;
    // 路径的总长度
    private float mPathLen;

    private float[] mCurPos = new float[2];

    public TextPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pathMeasure = new PathMeasure();
        mSourcePath = new Path();
        mDstPath = new Path();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(100);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        mPosPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPosPaint.setTextSize(20);
        mPosPaint.setColor(Color.BLACK);
        mPosPaint.setStyle(Paint.Style.STROKE);

        // 开启硬件加速
        setLayerType(View.LAYER_TYPE_HARDWARE, null);

        // 将文本路劲记录到mPath对象中,坐标位置起始点 50， 100
        mPaint.getTextPath(str, 0, str.length(), 50, 300, mSourcePath);

        pathMeasure.setPath(mSourcePath, false);

        // 获取路径的长度
        mPathLen = pathMeasure.getLength();
        while (pathMeasure.nextContour()) {
            mPathLen += pathMeasure.getLength();
        }

//        mPaint.setShader(new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.leak_canary_icon)
//                , Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect);

        mPaint.setShader(new LinearGradient(50, 300, rect.width(), rect.height(), new int[]{Color.RED, Color.LTGRAY, Color.GREEN},
                new float[]{0.3f, 0.4f, 0.3f}, Shader.TileMode.MIRROR));


        startAnim();
    }

    /**
     * 开启动画
     */
    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();

                pathMeasure.setPath(mSourcePath, false);
                mDstPath.reset();

                // 停止的节点信息
                float stop = mPathLen * value;
                // 当前轮廓的路径长度
                float len = pathMeasure.getLength();
                while (stop > len) {
                    // 获取轮廓信息到mDstPath中
                    pathMeasure.getSegment(0, len, mDstPath, true);
                    // 减去已经获取的轮廓路径
                    stop -= len;

                    // 判断是否有下一个轮廓，如果有切换到下一个轮廓
                    if (!pathMeasure.nextContour()) {
                        break;
                    }
                    // 获取下一个轮廓的path长度
                    len = pathMeasure.getLength();
                }

                // 获取剩余轮廓路径信息
                pathMeasure.getSegment(0, stop, mDstPath, true);

                pathMeasure.getPosTan(stop, mCurPos, null);

                // 设置路径完成后，将路径绘制到画布上显示
                postInvalidate();
            }
        });
        animator.setDuration(8_000);
        animator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mDstPath, mPaint);
        canvas.drawCircle(mCurPos[0], mCurPos[1], 10, mPosPaint);
    }
}
