package com.kyle.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/24
 */
public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
    }


    public void onStartAnim(View v){
        ObjectAnimator animator = ObjectAnimator.ofFloat(findViewById(R.id.tv_name), "rotation", 0f, 360f);
        animator.setDuration(400);
        animator.start();
    }
}
