package com.kyle.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.iv_scale)
    ImageView ivScale;
    @BindView(R.id.rv_layout)
    View rvLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // 获取CPU支持型号
        String cpu = Build.CPU_ABI + Build.CPU_ABI2;
        mTv.setText(cpu);


        PackageManager packageManager = getPackageManager();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        int size = runningAppProcesses.size();
        try {
            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            String name = applicationInfo.metaData.getString("NAME");
            Log.d(TAG, "onCreate: name >> " + name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(14);
        String content = "奥帆基地拉是否加拉斯对方\n家拉屎的飞机拉是否加拉斯飞机到解放分局卡拉是减肥可拉伸件";
        StaticLayout staticLayout = new StaticLayout(content,
                textPaint,
                100,
                Layout.Alignment.ALIGN_NORMAL,
                1, 0, false
        );

        Bitmap bitmap = Bitmap.createBitmap(100, 1024, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        staticLayout.draw(canvas);
        int lineStart = staticLayout.getLineStart(2);
        Log.d(TAG, "onCreate: " + content.substring(0, lineStart));

        rvLayout.setBackgroundDrawable(new BitmapDrawable(bitmap));

        int lineCount = staticLayout.getLineCount();
        Log.d(TAG, "lineCount: " + lineCount);
        Log.d(TAG, "lineStart: " + lineStart);

        // 6-16位字母和数字组成

        Log.d(TAG, String.format("%,3.2f", 888188880f));
        Log.d(TAG, String.format("%3.2f", 1f));
        Log.d(TAG, String.format("%x", 100));
        Log.d(TAG, String.format("%s", "15982299692"));
        Log.d(TAG, String.format("%+d", 10));
        Log.d(TAG, String.format("%+d", -10));
        Log.d(TAG, String.format("%-5d", -10));
        Log.d(TAG, String.format("%5d", -10));

        new Handler(Looper.getMainLooper())
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivScale.setImageResource(R.drawable.test);
                    }
                }, 1000);

        // TODO: 2017/10/9 内存泄露调试 
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    SystemClock.sleep(30000);
//                }
//            }
//        }).start();


        // 获取内存信息
    }


    private void getMemInfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getLargeMemoryClass();
        activityManager.getMemoryClass();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e(TAG, "onTrimMemory: " + level);
    }

    // 內存抖動
    private void doChurn() {
        final int rowLen = 10;
        final int len = 420000;
        for (int i = 0; i < rowLen; i++) {
            String[] strMatrix = new String[len];
            for (int j = 0; j < len; j++) {
                strMatrix[j] = String.valueOf(j);
            }
        }

        Log.d(TAG, "doChurn: done");
    }

    private void getBitmap() {
        BitmapRegionDecoder decoder;
    }

    @OnClick(R.id.tv)
    void onTvClicked(View view) {
//        MagicCircle magicCircle = ButterKnife.findById(this, R.id.magic);
//        magicCircle.startAnimation();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
