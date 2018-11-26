package com.kyle.library.viewpager;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/27
 */
class PagerAdapterWrapper extends PagerAdapter {

    private PagerAdapter innerPagerAdapter;

    PagerAdapterWrapper(PagerAdapter innerPagerAdapter) {
        this.innerPagerAdapter = innerPagerAdapter;
    }


    int getRealCount() {
        return innerPagerAdapter != null ? innerPagerAdapter.getCount() : 0;
    }

    int getRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }

        int realPosition = (position - 1) % realCount;
        // 当position = 0的时候， realPosition = count -1
        if (realPosition < 0) {
            realPosition += realCount;
        }
        return realPosition;
    }

    int getInnerPosition(int realPosition) {
        return realPosition + 1;
    }

    PagerAdapter getRealPagerAdapter() {
        return innerPagerAdapter;
    }

    @Override
    public int getCount() {
        int realCount = getRealCount();
        return realCount == 0 ? 0 : realCount + 2;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = getRealPosition(position);

        return innerPagerAdapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realPosition = getRealPosition(position);
        innerPagerAdapter.destroyItem(container, realPosition, object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return innerPagerAdapter.isViewFromObject(view, object);
    }


    @Override
    public void startUpdate(ViewGroup container) {
        innerPagerAdapter.startUpdate(container);
    }


    @Override
    public void finishUpdate(ViewGroup container) {
        innerPagerAdapter.finishUpdate(container);
    }

    @Override
    public void startUpdate(View container) {
        innerPagerAdapter.startUpdate(container);
    }


    @Override
    public void finishUpdate(View container) {
        innerPagerAdapter.finishUpdate(container);
    }

    @Override
    public Parcelable saveState() {
        return innerPagerAdapter.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        innerPagerAdapter.restoreState(state, loader);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return innerPagerAdapter.getPageTitle(position);
    }

    @Override
    public float getPageWidth(int position) {
        return innerPagerAdapter.getPageWidth(position);
    }


    static class ToDestroy {
        ViewGroup container;
        int position;
        Object object;

        public ToDestroy(ViewGroup container, int position, Object object) {
            this.container = container;
            this.position = position;
            this.object = object;
        }
    }

}
