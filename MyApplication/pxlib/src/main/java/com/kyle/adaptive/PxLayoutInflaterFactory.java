package com.kyle.adaptive;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/17
 */
public class PxLayoutInflaterFactory implements LayoutInflaterFactory {

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (parent instanceof TextView) {
            ScreenUtil.scaleProcessTextSize((TextView) parent);
        }
        ScreenUtil.scaleProcess(parent);
        return parent;
    }

}
