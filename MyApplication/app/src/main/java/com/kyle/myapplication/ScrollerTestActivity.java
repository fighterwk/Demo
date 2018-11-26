package com.kyle.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/30
 */
public class ScrollerTestActivity extends Activity {

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tv)
    TextView tv;

    private float mDownX;
    private float mDownY;

    private float mLastMoveY;
    private float mLastMoveX;

    private int mBorderLeft;
    private int mBorderTop;
    private int mBorderRight;
    private int mBorderBottom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        ButterKnife.bind(this);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        mBorderLeft = 0;
        mBorderTop = 0;
        mBorderRight = dm.widthPixels;
        mBorderBottom = 700;

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 记录按下的点和最后移动到的点
                        mLastMoveX = mDownX = event.getRawX();
                        mLastMoveY = mDownY = event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float x = event.getRawX();
                        float y = event.getRawY();

                        float scrollX = x - mLastMoveX;
                        float scrollY = y - mLastMoveY;

                        mLastMoveY = y;
                        mLastMoveX = x;

                        int l = tv.getLeft();
                        int r = tv.getRight();
                        int t = tv.getTop();
                        int b = tv.getBottom();

                        // 负 左， 正 右
                        if (scrollX < 0) {
                            // 左边界
                            if (l + scrollX < mBorderLeft) {
                                scrollX = -(l - mBorderLeft);
                            }
                        } else {
                            // 右边界
                            if (r + scrollX > mBorderRight) {
                                scrollX = mBorderRight - r;
                            }
                        }


                        // 负 上， 正 下
                        if (scrollY < 0) {
                            // 上边界
                            if (t + scrollY < mBorderTop) {
                                scrollY = -(t - mBorderTop);
                            }
                        } else {
                            // 下边界
                            if (b + scrollX > mBorderBottom) {
                                scrollY = mBorderBottom - b;
                            }
                        }


//                        tv.layout((int) (tv.getLeft() + scrollX), (int) (tv.getTop() + scrollY + 0.5f),
//                                (int) (tv.getRight() + scrollX), (int) (tv.getBottom() + scrollY));

                        tv.layout(tv.getLeft(), (int) (tv.getTop() + scrollY + 0.5f),
                                tv.getRight(), (int) (tv.getBottom() + scrollY + 0.5f));
                        break;

                    case MotionEvent.ACTION_UP:

                        break;
                }


                return true;
            }
        });

    }


}
