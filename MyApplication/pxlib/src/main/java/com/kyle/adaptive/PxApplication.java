package com.kyle.adaptive;

import android.app.Application;
import android.view.LayoutInflater;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/17
 */
public class PxApplication extends Application {

    /**
     * 基于屏幕宽度进行适配
     */
    private static final int BASE_DEFAULT_SCREEN_WIDTH = 720;

    LayoutInflater inflater;

    @Override
    public void onCreate() {
        super.onCreate();
        ScreenUtil.init(this, BASE_DEFAULT_SCREEN_WIDTH);

//        inflater = LayoutInflater.from(this);
//        LayoutInflaterCompat.setFactory(inflater, new PxLayoutInflaterFactory());
    }

//    @Override
//    public Object getSystemService(String name) {
//        System.out.println(">>>>>>>> getSystemService()");
//        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
//            if (inflater == null)
//                inflater = (LayoutInflater) super.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            return inflater;
//        }
//        return super.getSystemService(name);
//    }
}
