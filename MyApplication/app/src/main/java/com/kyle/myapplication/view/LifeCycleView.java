package com.kyle.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/15
 */
public class LifeCycleView extends TextView {
    private static final String TAG = "LifeCycle";

    public LifeCycleView(Context context) {
        super(context);
    }

    public LifeCycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "-----------> LifeCycleView: onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "-----------> LifeCycleView: onLayout");
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.i(TAG, "-----------> LifeCycleView: layout");
    }
}
