package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/7
 */
public class ProgressView extends View {
    private static String TAG = ProgressView.class.getSimpleName();
    private int W = 30;
    private int H = 150;

    private Bitmap bmBg; // 背景框
    private Bitmap bmDst; // 实际效果图
    private Paint mPaint; // 画笔渲染

    private int progress = 50;  // 进度

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);  // 绘制边框
        mPaint.setStrokeWidth(1);   // 边框宽度:1
        mPaint.setColor(Color.BLUE); // 颜料颜色

        bmBg = makeBgBm(W, H);
        bmDst = makeDstBm(W, H);

        // 关闭硬件加速
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        /**
         * canvas.saveLayer(left, top, right, bottom, paint, canvasFlag);
         * canvas.restoreToCount();
         */

        run();
    }

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    progress += 1;
                    SystemClock.sleep(100);

                    if (progress > 100) {
                        progress = 0;
                    }
                    postInvalidate();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 在(300, 300) 坐标点开始绘制
        canvas.translate(300, 300);
        canvas.save();

        Path path = new Path();
        path.moveTo(0, 0);
        // [0-50], [50-100]
        // [0-60]
        int y = new Random().nextInt(60);
        int offset = 30 - y;
        path.cubicTo(new Random().nextInt(50), offset, new Random().nextInt(50) + 50, -offset, 100, 0);
        path.lineTo(100, 100);
        path.lineTo(0, 100);
        path.lineTo(0, 0);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, mPaint);

//         绘制背景
//        canvas.drawBitmap(bmBg, 0, 0, mPaint);
//        canvas.restore();
//
//        // 绘制填充值(进度和效果进行合并 取交集进行填充, 备注:原样图和源图的尺寸需要相同,不然会出现无法取出两图不同效果)
//        canvas.drawBitmap(bmDst, 0, 0, mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        Bitmap srcBm = makeSrcBm(W, H, progress);
//        canvas.drawBitmap(srcBm, 0, 0, mPaint);
//
//        mPaint.setXfermode(null);


//        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//        p.setColor(Color.RED);
//        p.setStyle(Paint.Style.STROKE);
//        RectF rectF = new RectF(0, 0, 100, 100);
//        canvas.drawRect(rectF, p);
//        p.setColor(Color.BLACK);
//        p.setStyle(Paint.Style.FILL);
//        canvas.drawArc(rectF, 0, -180, true, p);
    }

    Bitmap makeBgBm(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas c = new Canvas(bm);
        Path path = new Path();

        p.setColor(Color.BLUE);
        p.setStrokeWidth(1);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);

        path.moveTo(0, h);
        path.lineTo(w, h);
        path.lineTo(w, w / 2);
        path.addArc(new RectF(0, 0, w, w), 0, -180);
        path.lineTo(0, h);

        c.drawPath(path, p);
        return bm;
    }


    Bitmap makeDstBm(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas c = new Canvas(bm);
        Path path = new Path();

        p.setColor(Color.BLUE);
        p.setStrokeWidth(1);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);

        path.moveTo(0, h);
        path.lineTo(w, h);
        path.lineTo(w, w / 2);
        path.addArc(new RectF(0, 0, w, w), 0, -180);
        path.lineTo(0, h);
        path.close();

        c.drawPath(path, p);
        return bm;
    }


    Bitmap makeSrcBm(int w, int h, int progress) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas c = new Canvas(bm);
        Path path = new Path();

        p.setColor(Color.BLUE);
        p.setStrokeWidth(1);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.FILL);


        int hp = (int) (h * (progress / 100f));

        Point left = new Point(w, h - hp);
        Point right = new Point(0, h - hp);

        path.moveTo(0, h);
        path.lineTo(w, h);
        path.lineTo(left.x, left.y);

        int yRandom = Math.min(w, h - hp);

//        path.lineTo(right.x, right.y);
        // 控制点1 [0-w/2], 控制点2 [w/2 - w]
        Log.d(TAG, "makeSrcBm: yRandom >> " + yRandom);
        path.cubicTo(new Random().nextInt(w / 2),
                new Random().nextInt(yRandom),
                new Random().nextInt(w / 2) + (w / 2),
                new Random().nextInt(yRandom), right.x, right.y);

        path.lineTo(0, h);
//        path.close();

        c.drawPath(path, p);
        return bm;
    }
}
