package com.kyle.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/18
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupLeakCanary();
    }

    protected void setupLeakCanary() {
//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
