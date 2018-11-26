package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/2/7
 */
public class TestView extends View {

    private Paint mPaint;
    private Bitmap circleBm;

    private Path path = new Path();

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int w = getMeasuredWidth();
        int h = getMeasuredHeight();

//        circleBm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
//        Canvas c = new Canvas(circleBm);
//
//        c.drawCircle(w / 2, h / 2, w / 2, mPaint);

        initPath();

    }

    private void initPath() {

        path.setFillType(Path.FillType.INVERSE_WINDING);
        path.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2,
                getMeasuredWidth() / 4, Path.Direction.CW);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
//        canvas.clipRect(100, 100, 200, 200);
//        canvas.drawBitmap(circleBm, 0, 0, null);

        canvas.clipPath(path);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);

        canvas.restore();
    }
}
