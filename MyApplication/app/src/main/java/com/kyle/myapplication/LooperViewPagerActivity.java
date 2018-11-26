package com.kyle.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyle.library.viewpager.LooperViewPager;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description描述:LooperViewPager测试. 测试：分别使用1、2、3、4个数据源的Adapter测试是否可以无限循环。
 * 使用两种数据源的方式构建Adapter
 * 1). 使用文本原始数据源，在Adapter instantiateItem方法中执行创建View操作
 * 2). 直接使用View数据源。
 * <p>
 * 结果: 使用原始数据源，在instantiateItem方法中执行创建View操作 在1+数据源中可以无限循环。
 * 直接使用View作为数据源会报错。
 * <p>
 * 直接使用原始数据源会导致 instantiateItem 方法重复执行，导致反复的创建View和回收View出现性能问题。
 * 解决，可以使用ListView设计方式，提供数据源和layout布局，创建和销毁View的工作交给Adapter内部实现。
 * <p>
 * 需要编写一个 管理数据源和layout布局，创建View的一个帮助类，等同于是定制了一个规则，使用者需要按照制定的规则进行使用。
 * @Author作者: Kyle
 * @Date日期: 2017/9/27
 */
public class LooperViewPagerActivity extends Activity {

    private static String TAG = LooperViewPagerActivity.class.getSimpleName();

    @BindView(R.id.view_pager)
    LooperViewPager viewPager;
    List<String> data = new ArrayList<>();
    List<TextView> viewList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_view_pager);
        ButterKnife.bind(this);
        // 1个
//        data.add(String.valueOf("1"));

        // 2个
//        for (int i = 0; i < 2; i++) {
//            data.add(String.valueOf(i));
//        }
        // 三个
        for (int i = 0; i < 8; i++) {
            data.add(String.valueOf(i));
        }

        // 通过数据源创建View
//        for (String s : data) {
//            viewList.add(obtainTextView(s));
//        }


        viewPager.setAdapter(new PagerAdapter() {

            Queue<SoftReference<View>> toCache = new LinkedList<SoftReference<View>>();

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = null;
                SoftReference<View> viewSoftReference = toCache.poll();
                if (viewSoftReference != null) {
                    view = viewSoftReference.get();
                }

                if (view == null) {
                    view = obtainTextView(data.get(position));
                }

                bindData(view, position);

                container.addView(view);
                return view;
            }

            private void bindData(View view, int position) {
                TextView textView = (TextView) view;
                textView.setText(data.get(position));
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View view = (View) object;
                toCache.offer(new SoftReference<View>(view));
                container.removeView(view);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }


    static int createViewNum = 0;

    private TextView obtainTextView(String value) {
        createViewNum++;
        TextView textView = new TextView(LooperViewPagerActivity.this);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        textView.setGravity(Gravity.CENTER);
        textView.setText(value);
        Random random = new Random();
        textView.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        Log.d(TAG, "obtainTextView: createNum : " + createViewNum);
        return textView;
    }

}
