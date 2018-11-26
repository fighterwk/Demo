package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kyle.myapplication.R;


/**
 * 创建者: Kyle
 * 创建时间: 2017/9/5.
 */

public class XfermodeView extends View {

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint fillPaint;
    private Path path;
    private Bitmap picPm;

    private void init(){
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setXfermode(new PorterDuffXfermode(
                PorterDuff.Mode.SRC_IN
        ));
        fillPaint.setColor(Color.DKGRAY);
        path = new Path();
        picPm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pic)
        , 100, 100, false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        path.reset();
        int width = picPm.getWidth();
        int height = picPm.getHeight();
        path.moveTo(0, 0);
        path.lineTo(0, height);
        path.lineTo(width, height);
        path.lineTo(width, 0);
        path.cubicTo(65, 20, 25, 0, 0, 0);
        path.close();

        canvas.drawBitmap(picPm, 0, 0, fillPaint);
        canvas.drawPath(path, fillPaint);
    }
}
