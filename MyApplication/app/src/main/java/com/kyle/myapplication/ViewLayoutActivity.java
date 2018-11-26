package com.kyle.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/13
 */
public class ViewLayoutActivity extends AppCompatActivity {

    @BindView(R.id.vi)
    View vi;
    @BindView(R.id.btn_move)
    Button btnMove;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_layout);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_move)
    public void onMoveClicked(View view){
        vi.layout(0, 0, 10, 10);
    }


}
