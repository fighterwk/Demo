package com.kyle.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.kyle.myapplication.fragment.MenuBottomFragment;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/16
 */
public class WindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.WindowTheme);
//        setTheme(R.style.AppTheme);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        Window window = getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);



        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = displayMetrics.widthPixels;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
//        attributes.alpha = 0.3f;
//        attributes.dimAmount = 0.3f;
        attributes.gravity = Gravity.BOTTOM;

        getWindow().setAttributes(attributes);


        setContentView(R.layout.activity_window);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, Fragment.instantiate(this, MenuBottomFragment.class.getName()))
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static void startActivity(Activity context) {
        Intent intent = new Intent(context, WindowActivity.class);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.menu_bottom_in, 0);
    }
}
