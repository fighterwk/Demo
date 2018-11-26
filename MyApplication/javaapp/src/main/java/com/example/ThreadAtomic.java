package com.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description描述: 线程原子性
 * @Author作者: Kyle
 * @Date日期: 2018/3/21
 */
public class ThreadAtomic {


    public static void main(String[] args) {
        try {
            threadMemoryShare();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static volatile boolean bChanged;
    static volatile int num;
    static AtomicInteger atomicValue = new AtomicInteger();
    static int count;
    static Counter counter = new Counter();


    static class Counter {
        int value;

        public synchronized int getValue() {
            return value;
        }

        public synchronized int increment() {
            return ++value;
        }
    }

    static void threadMemoryShare() throws InterruptedException {


//        // 创建10个线程
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 做1000次循环的累加
//                    for (int j = 0; j < 1000; j++) {
//                        num++;
//                        atomicValue.getAndIncrement();
//                        count++;
//                        counter.increment();
//                    }
//                }
//            }).start();
//        }
//
//        // 等待线程执行完成
//        Thread.sleep(5000);
//
//        // 打印出计算的值
//        System.out.println("volatile num:" + num);
//        System.out.println("Count:" + count);
//        System.out.println("counter:" + counter.getValue());
//        System.out.println("atomicValue:" + atomicValue);


        new Thread("thread_1") {

            @Override
            public void run() {
                for (; ; ) {

                    // 原子性分析
                    // bChanged = !bChanged 拆分执行步骤
                    // 1. 从主内存中获取bChanged值
                    // 2. 执行 !bChanged, 这里的bChanged值也是直接在主内存中获取值
                    // 3. 执行 bChanged == (!bChanged), 在本例子中，thread_2在不停的改变bChanged的值，
                    // 所以bChanged的值和 表达式中!bChanged中的bChanged值是可能不一样的的。

                    // 非原子性分析
                    // bChanged = !bChanged 拆分执行步骤
                    // 1. 从主内存中获取bChanged值，并且拷贝到当前线程中的 工作内存中
                    // 2. 执行 !bChanged, 这里的bChanged值直接在 当前线程中的 工作内存中获取.
                    // 3. 如果bChanged值为false , 那么 !bChanged 值为 true，所以 bChanged == (!bChanged)在非原子性的状态下 为false

                    if (bChanged == (!bChanged)) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread("thread_2") {

            @Override
            public void run() {
                for (; ; ) {
                    // 原子性分析

                    // 非原子性分析
                    // bChanged = !bChanged 拆分执行步骤
                    // 1. 从主内存中获取bChanged值，并且拷贝到当前线程中的 工作内存中
                    // 2. 执行 !bChanged, 这里的bChanged值直接在 当前线程中的 工作内存中获取.
                    // 3. 将 !bChanged 的值赋值给 bChanged，执行完成将bChanged的值保存到主内存中
                    bChanged = !bChanged;
                }
            }
        }.start();
    }
}
