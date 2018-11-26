package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.kyle.myapplication.fragment.MenuPopFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/13
 */
public class MenuPopActivity extends AppCompatActivity {
    private static String TAG = MenuPopActivity.class.getSimpleName();

    @BindView(R.id.tv_log)
    TextView tvLog;

    StringBuffer buffLog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buffLog = new StringBuffer();

        setContentView(R.layout.activity_menu_pop);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.fl_content, new MenuPopFragment());
        tr.commit();


        ButterKnife.bind(this);


        tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        printLog("onBackPressed()");
//        if (popupWindow.isShowing()) {
//            popupWindow.dismiss();
//            return;
//        }

        try {
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
            printLog(e.getLocalizedMessage());
        }
    }


    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public void printLog(String log) {
        buffLog.append("[").append(dateFormat.format(new Date()))
                .append("]").append(log).append("\n");
        tvLog.setText(buffLog.toString());
        Log.d(TAG, "printLog: " + log);
    }

}
