package com.kyle.myapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @Description描述: 在Activity上显示Dialog， 点击home键后 Activity的生命周期变化
 * @Author作者: Kyle
 * @Date日期: 2017/9/15
 */
public class LifecycleActivity extends FragmentActivity {
    private static String TAG = LifecycleActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_lifecycle);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: ");

        Configuration configuration = getResources().getConfiguration();
        int orientation = configuration.orientation;
        if (Configuration.ORIENTATION_PORTRAIT == orientation){
            Log.d(TAG, "onConfigurationChanged: orientation:竖屏");
        }else if (Configuration.ORIENTATION_LANDSCAPE == orientation){
            Log.d(TAG, "onConfigurationChanged: orientation:横屏");
        }

    }

    public void onShowDialog(View view) {
        PopupWindow pw = new PopupWindow(this);
        pw.setWidth(500);
        pw.setHeight(100);
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText("hello");
        pw.setContentView(new TextView(this));
        pw.showAsDropDown(view);
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setTitle("温馨提示").setMessage("Dialog 显示点击Home键，查看依附的Activity生命周期变化")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
