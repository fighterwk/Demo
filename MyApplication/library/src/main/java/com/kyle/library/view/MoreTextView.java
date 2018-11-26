package com.kyle.library.view;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @Description描述:
 * 设置默认显示多少字，超出部分使用【...查看更多】代替。
 * 点击【查看更多】展示完所有的文本内容。在末尾加上【收起】标签。
 * 点击【收起】还原初始状态。
 *
 * 技术点:
 * StaticLayout
 * Paint.measureText
 * Paint.breakText
 * @Author作者: Kyle
 * @Date日期: 2017/9/28
 */
public class MoreTextView extends TextView {

    // 默认显示文本的个数
    private int showTextNum;

    // 原始文本


    public MoreTextView(Context context) {
        super(context);
    }

    public MoreTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoreTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MoreTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    

}
