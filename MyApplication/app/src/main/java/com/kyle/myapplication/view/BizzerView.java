package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/16
 */
public class BizzerView extends FrameLayout {

    private static String TAG = BizzerView.class.getSimpleName();

    public BizzerView(@NonNull Context context) {
        super(context);
    }

    public BizzerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BizzerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BizzerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0, getHeight() - 200);
        path.quadTo(getWidth() / 2, getHeight(), getWidth(), getHeight() - 200);
        path.lineTo(getWidth(), 0);

        paint.setColor(Color.parseColor("#00ffcc"));
        canvas.drawPath(path, paint);

        canvas.restore();
        super.dispatchDraw(canvas);
    }
}
