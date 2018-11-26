package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.myapplication.view.LifeCycleView;
import com.kyle.myapplication.view.MyViewGroup;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/15
 */
public class ViewLifecycleActivity extends AppCompatActivity {

    ViewGroup vg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lifecycle);
        vg = (ViewGroup) findViewById(R.id.vg);
    }


    public void onClear(View v) {
        Log.i(MyViewGroup.TAG, "-------> 移除一个控件");
        vg.removeView(vg.getChildAt(0));
    }

    public void onAdd(View v) {
        Log.i(MyViewGroup.TAG, "-------> 添加一个控件");
        com.kyle.myapplication.view.LifeCycleView view = new com.kyle.myapplication.view.LifeCycleView(this);
        view.setText("添加");
        vg.addView(view, 0);
    }

    public void onModify(View v) {
        Log.i(MyViewGroup.TAG, "-------> 修改一个控件内容");
        com.kyle.myapplication.view.LifeCycleView view = (LifeCycleView) vg.getChildAt(0);
        view.setText("修改-----------");
    }

}
