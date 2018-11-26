package com.kyle.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kyle.myapplication.view.Riffle2View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/2/7
 */
public class TestActivity extends AppCompatActivity {


    Riffle2View vi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        vi = (Riffle2View) findViewById(R.id.vi);

        ObjectAnimator animator = ObjectAnimator.ofInt(vi, "radius", 130, 600);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);

        animator.start();
    }
}
