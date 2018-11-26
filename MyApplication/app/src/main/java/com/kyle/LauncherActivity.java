package com.kyle;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.kyle.myapplication.AnimatorActivity;
import com.kyle.myapplication.DataBindingActivity;
import com.kyle.myapplication.InputNumActivity;
import com.kyle.myapplication.LifecycleActivity;
import com.kyle.myapplication.LooperViewPagerActivity;
import com.kyle.myapplication.MainActivity;
import com.kyle.myapplication.MenuPopActivity;
import com.kyle.myapplication.MyWebViewActivity;
import com.kyle.myapplication.PermissionActivity;
import com.kyle.myapplication.ReadMoreActivity;
import com.kyle.myapplication.ScrollerTestActivity;
import com.kyle.myapplication.TestActivity;
import com.kyle.myapplication.TextPathActivity;
import com.kyle.myapplication.ViewLayoutActivity;
import com.kyle.myapplication.ViewLifecycleActivity;
import com.kyle.myapplication.WindowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/27
 */
public class LauncherActivity extends ListActivity {
    private final List<MyComponentName> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add(new MyComponentName(TestActivity.class));
        list.add(new MyComponentName(MenuPopActivity.class));
        list.add(new MyComponentName(ScrollerTestActivity.class));
        list.add(new MyComponentName(MainActivity.class));
        list.add(new MyComponentName(LooperViewPagerActivity.class));
        list.add(new MyComponentName(LifecycleActivity.class));
        list.add(new MyComponentName(DataBindingActivity.class));
        list.add(new MyComponentName(ReadMoreActivity.class));
        list.add(new MyComponentName(ViewLayoutActivity.class));
        list.add(new MyComponentName(PermissionActivity.class));
        list.add(new MyComponentName(MyWebViewActivity.class));
        list.add(new MyComponentName(WindowActivity.class));
        list.add(new MyComponentName(AnimatorActivity.class));
        list.add(new MyComponentName(InputNumActivity.class));
        list.add(new MyComponentName(TextPathActivity.class));
        list.add(new MyComponentName(ViewLifecycleActivity.class));

        setListAdapter(new ArrayAdapter<MyComponentName>(this,
                android.R.layout.simple_list_item_1, list));

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyComponentName myComponentName = list.get(position);
                Intent intent = new Intent(LauncherActivity.this, myComponentName.cls);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        Runtime.getRuntime().availableProcessors();
//        MediaStore.Images.Media.insertImage()
//        MediaScannerConnection.scanFile();
    }



    private static class MyComponentName {
        private Class<?> cls;

        public MyComponentName(Class<?> cls) {
            this.cls = cls;
        }

        @Override
        public String toString() {
            return cls.getSimpleName();
        }
    }
}
