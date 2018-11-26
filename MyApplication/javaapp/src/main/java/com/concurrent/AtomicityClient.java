package com.concurrent;

/**
 * @Description描述: 数据原子性
 * @Author作者: Kyle
 * @Date日期: 2018/10/16
 */
public class AtomicityClient implements Runnable {

    //    int num = 0;
//    AtomicInteger num = new AtomicInteger(0);

    //    volatile int num = 0;
    Num num = new Num();

    public void increase() {
//        num++;
//        num.incrementAndGet();
        num.increment();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            increase();
        }
    }

    public int getNum() {
//        return num.get();
//        return num;
        return num.num;
    }

    class Num {
        int num;

        public void increment() {
            synchronized (Num.class) {
                num++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicityClient atomicity = new AtomicityClient();
        Thread t1 = new Thread(atomicity);
        Thread t2 = new Thread(atomicity);

        t1.start();
        t2.start();

        t1.join();
        System.out.println("t1.join:" + atomicity.getNum());
        t2.join();
        System.out.println("t2.join:" + atomicity.getNum());

        System.out.println("num:" + atomicity.getNum());
    }
}
