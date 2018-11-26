package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/6
 */
public class CanvasView extends View {

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    Paint mPaint;

    Bitmap dstBm;
    Bitmap srcBm;

    private int progress = 50; // 进度值[0-100]
    private int width = 50;
    private int height = 300;

    private void init() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        mPaint = paint;

        dstBm = makeDstBm(width, height);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        run();
    }

    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    progress += 5;
                    SystemClock.sleep(500);
                    if (progress > 100) {
                        progress = 0;
                        continue;
                    } else {
                        postInvalidate();
                    }
                }
            }
        }).start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0, 0, width, height);
        int rx = width / 2;
        canvas.drawRoundRect(rectF, rx, 0, mPaint);
        mPaint.setFilterBitmap(false);
//        int layerId = canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
        {
            canvas.drawBitmap(dstBm, 0, 0, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            mPaint.setColor(Color.YELLOW);
            Bitmap srcBm = makeSrcBm(width, height, progress);
            canvas.drawBitmap(srcBm,
                    0, 0, mPaint);

            mPaint.setXfermode(null);
        }
//        canvas.restoreToCount(layerId);
    }

    public synchronized void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }


    // dst
    Bitmap makeDstBm(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.DKGRAY);
        int r = h / 2;
        p.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0, 0, w, h);
        c.drawRoundRect(rectF, r, r, p);
        return bm;
    }

    // src  dst 和src 尺寸大小需要一样
    Bitmap makeSrcBm(int w, int h, int progress) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        float srcW = w * (progress / 100f);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(srcW, 0);

//
//        RectF rectF = new RectF(0, 0, w * (progress / 100f), h);
//        c.drawRect(rectF, p);
        return bm;
    }


}
