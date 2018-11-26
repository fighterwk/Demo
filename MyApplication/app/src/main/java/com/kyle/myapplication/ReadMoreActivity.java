package com.kyle.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.kyle.myapplication.view.ReadMoreTextView;

import org.greenrobot.eventbus.EventBus;

import static com.kyle.myapplication.R.id.text3;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/9
 */
public class ReadMoreActivity extends AppCompatActivity {

    private static String TAG = ReadMoreActivity.class.getSimpleName();

    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutInflater = getLayoutInflater().cloneInContext(this);
//        layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.d(TAG, "onCreateView: name:" + name);
                return null;
            }
        });


        setContentView(R.layout.activity_read_more);

        final ReadMoreTextView textView = (ReadMoreTextView) findViewById(text3);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(R.string.lorem_ipsum2);
            }
        }, 1000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                Configuration configuration = getResources().getConfiguration();
                Log.d(TAG, "" + displayMetrics.widthPixels + " >> " + displayMetrics.heightPixels
                        + " :: " + displayMetrics.densityDpi
                        + " orientation: " + (configuration.orientation == Configuration.ORIENTATION_PORTRAIT ? "竖" : "横"));
            }
        });


        EventBus.getDefault().post(new Bundle());

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    //    @NonNull
//    @Override
//    public LayoutInflater getLayoutInflater() {
//        Log.d(TAG, "getLayoutInflater: ");
//        return layoutInflater;
//    }
//
//    @Override public Object getSystemService(@NonNull String name) {
//        if (name.equals(LAYOUT_INFLATER_SERVICE)) {
//            if (layoutInflater == null) {
//                layoutInflater = (LayoutInflater) super.getSystemService(name);
//            }
//            return layoutInflater;
//        }
//        return super.getSystemService(name);
//    }
}
