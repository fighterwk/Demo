package com.kyle.library.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/30
 */
public class ScrollerTest extends ViewGroup {

    private static String TAG = ScrollerTest.class.getSimpleName();

    private Scroller mScroller;
    private float mDownX;
    private float mMoveX;
    private float mLastMoveX;

    private float touchSlop;

    public ScrollerTest(Context context) {
        super(context);
        init(context);

    }

    public ScrollerTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ScrollerTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ScrollerTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        touchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();

        Log.d(TAG, "touchSlop : " + touchSlop);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 测量子控件
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            // TODO: 2017/10/30 错误，在测量子控件的时候，应该使用 measureChild 而不是 Child类的measure
//            child.measure(widthMeasureSpec, heightMeasureSpec);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.layout(i * child.getMeasuredWidth(), 0,
                        (i + 1) * child.getMeasuredWidth(),
                        child.getMeasuredHeight());
            }
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.forceFinished(true);
                }

                mDownX = ev.getRawX();
                mLastMoveX = ev.getRawX();

                break;

            case MotionEvent.ACTION_MOVE:
                mMoveX = ev.getRawX();
                float move = Math.abs(mMoveX - mLastMoveX);
                mLastMoveX = mMoveX;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (move > touchSlop) {
                    return true;
                }

                break;

            case MotionEvent.ACTION_CANCEL:
                if (!mScroller.isFinished()) {
                    mScroller.forceFinished(true);
                }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mMoveX = event.getRawX();
                int scrollX = (int) (mLastMoveX - mMoveX);
                scrollBy(scrollX, 0);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
