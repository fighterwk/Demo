package com.example.reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * @Description描述:
 * WeakReference执行System.gc();就会被垃圾回收机制给清理掉
 * SoftReference在内存不足时，应用需要更多的内存时 被垃圾回收机给清理
 * @Author作者: Kyle
 * @Date日期: 2017/11/15
 */
public class Service {

    private Reference<Callback> reference;

    public Service(Callback callback) {
        this.reference = new SoftReference<Callback>(callback);
    }


    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Callback callback = reference.get();
                if (callback != null) {
                    callback.onCall();
                } else {
                    System.out.println("--- callback == null");
                }
            }
        }).start();
    }
}
