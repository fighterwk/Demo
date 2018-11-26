package com.kyle.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyle.myapplication.bean.PersonInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/30
 */
public class DataBindingActivity extends AppCompatActivity {

    PersonInfoBean infoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        // databinding
        com.kyle.myapplication.CustomBinding viewDataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        PersonInfoBean infoBean = new PersonInfoBean("Kyle", 1, 26);
        viewDataBinding.setPerson(infoBean);
        this.infoBean = infoBean;


    }

    public void onModify(View v) {
        this.infoBean.setSex(2);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventSend(Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
