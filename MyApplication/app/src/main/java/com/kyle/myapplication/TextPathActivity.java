package com.kyle.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kyle.myapplication.view.TextPathView;

public class TextPathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_path);
    }

    public void onStartAnimClicked(View v) {
        TextPathView pathView = (TextPathView) v;
        pathView.startAnim();
    }
}
