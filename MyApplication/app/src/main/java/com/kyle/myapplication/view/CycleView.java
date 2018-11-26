package com.example.administrator.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/8
 */
public class CycleView extends View {
    private Paint paint;

    private int width;
    private int height;

    public CycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(0);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.RED);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cycleFloat = (float) (2 * Math.PI) / width;  // 得到一个周期的单元
        canvas.translate(0, 100);
        for (int i = 0; i < width; i++) {
            canvas.drawPoint(i, (float) Math.sin(i * cycleFloat), paint);
        }
    }
}
