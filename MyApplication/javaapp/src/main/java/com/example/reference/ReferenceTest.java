package com.example.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * -verbose:gc -Xms8M -Xmx8M -Xmn4M
 *
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/15
 */
public class ReferenceTest {

    //创建一个引用队列
    private static ReferenceQueue<RefObject> rq = new ReferenceQueue<>();
    private static int size = 12;

    /**
     * 验证队列
     */
    public static void checkQueue() throws InterruptedException {
        Reference<? extends RefObject> ref;
        while ((ref = rq.remove()) != null) {
            System.out.println("回收了: " + ref);
            ref.clear();
        }
    }

    public static void testStrongReference() {
        System.out.println("--------执行强引用开始-------");
        Set<RefObject> sa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            sa.add(new RefObject("StrongReference" + i));
        }
        System.gc();
        System.out.println("--------执行强引用结束-------");
    }

    public static void testSoftReference() {
        System.out.println("--------执行软引用开始-------");
        Set<SoftReference<RefObject>> sa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            SoftReference<RefObject> ref = new SoftReference<>(new RefObject("SoftReference " + i), rq);
            printRef(ref);
            sa.add(ref);
        }
        System.runFinalization();
        System.gc();
        System.out.println("--------执行软引用结束-------");
    }

    public static void testWeakReference() {
        System.out.println("--------执行弱引用开始-------");
        Set<WeakReference<RefObject>> wa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            WeakReference<RefObject> ref = new WeakReference<>(new RefObject("WeakReference " + i), rq);
            printRef(ref);
            wa.add(ref);
        }
        // 收到GC，会对弱引用进行回收
//        System.gc();
        System.out.println("--------执行软弱用结束-------");
    }

    public static void testPhantomReference() {
        System.out.println("--------执行虚引用开始-------");
        Set<PhantomReference<RefObject>> pa = new HashSet<>();
        for (int i = 0; i < size; i++) {
            PhantomReference<RefObject> ref =
                    new PhantomReference<>(new RefObject("PhantomReference " + i), rq);
            printRef(ref);
            pa.add(ref);
        }
        System.gc();
        System.out.println("--------执行虚弱用结束-------");
    }

    private static final void printRef(Reference ref) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("创建 ref:" + ref + " obj:" + ref.get());
    }

    public static void main(String args[]) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                        checkQueue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        testStrongReference();
//        testWeakReference();
        testPhantomReference();

//        testSoftReference();
//        testWeakReference();
    }


    static class RefObject {
        private static final int _1MB = 1024 * 1024;
        byte[] bytes = new byte[_1MB * 2];

        String str;

        public RefObject(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
