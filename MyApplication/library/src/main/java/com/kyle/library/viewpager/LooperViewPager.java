package com.kyle.library.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @Description描述: 主要工作是对PagerAdapterWrapper包装的数据进行解包
 * @Author作者: Kyle
 * @Date日期: 2017/9/27
 */
public class LooperViewPager extends ViewPager {

    private PagerAdapterWrapper pagerAdapterWrapper;
    private OnPageChangeListener outPageChangeListener;

    public LooperViewPager(Context context) {
        super(context);
        init();
    }

    public LooperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        super.addOnPageChangeListener(onPageChangeListener);
    }


    @Override
    public PagerAdapter getAdapter() {
        return pagerAdapterWrapper != null ? pagerAdapterWrapper.getRealPagerAdapter() : null;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        pagerAdapterWrapper = new PagerAdapterWrapper(adapter);
        super.setAdapter(pagerAdapterWrapper);
        setCurrentItem(0, false);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, true);
    }

    @Override
    public int getCurrentItem() {
        return pagerAdapterWrapper != null ? pagerAdapterWrapper.getRealPosition(super.getCurrentItem())
                : 0;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.outPageChangeListener = onPageChangeListener;
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        setOnPageChangeListener(listener);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {

        if (pagerAdapterWrapper == null) {
            throw new RuntimeException("adapter not is null");
        }
        int innerPosition = pagerAdapterWrapper.getInnerPosition(item);

        super.setCurrentItem(innerPosition, smoothScroll);
    }


    // 转换下标，将包装后的下标解包装成原来的下标.
    private final OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (pagerAdapterWrapper == null) {
                return;
            }

            int realPosition = pagerAdapterWrapper.getRealPosition(position);
            if ((position == 0 || position == pagerAdapterWrapper.getCount() - 1) &&
                    positionOffset == 0) {
                setCurrentItem(realPosition, false);
            }

            if (outPageChangeListener != null) {
                outPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
            }

        }

        @Override
        public void onPageSelected(int position) {
            if (pagerAdapterWrapper == null) {
                return;
            }
            int realPosition = pagerAdapterWrapper.getRealPosition(position);
            if (outPageChangeListener != null) {
                outPageChangeListener.onPageSelected(realPosition);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (pagerAdapterWrapper == null) {
                return;
            }

            int position = LooperViewPager.super.getCurrentItem();
            int realPosition = pagerAdapterWrapper.getRealPosition(position);

            // 滑动结束状态：如果当前处于包装的第一和最后坐标，偏移到原始位置
            if (state == ViewPager.SCROLL_STATE_IDLE
                    && (position == 0 || position == pagerAdapterWrapper.getCount() - 1)) {
                setCurrentItem(realPosition, false);
            }

            if (outPageChangeListener != null) {
                outPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    };
}
