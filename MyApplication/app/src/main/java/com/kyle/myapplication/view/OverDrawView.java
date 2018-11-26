package com.kyle.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/23
 */
public class OverDrawView extends View{
    public OverDrawView(Context context) {
        super(context);
    }

    public OverDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OverDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
