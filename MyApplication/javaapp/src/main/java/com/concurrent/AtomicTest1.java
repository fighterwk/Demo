package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/18
 */
public class AtomicTest1 {

    static AtomicInteger atoIn = new AtomicInteger(200);

    static AtomicStampedReference<Integer>
            atomicStamped = new AtomicStampedReference<>(100, 0);


    public static void main(String[] args) {
        boolean b = atoIn.compareAndSet(100, 300);
        atoIn.compareAndSet(200, 100);
        System.out.println("flag:" + b +
                " value:" + atoIn.get());

        int time = atomicStamped.getStamp();
        System.out.println("time = " + time);
        // 数据更新为 100
        boolean flag = atomicStamped.compareAndSet(100, Integer.valueOf(500), time, time + 1);
        System.out.println("flag:" + flag);
        int time2 = atomicStamped.getStamp();
        // 数据由100 更新为 300
        atomicStamped.compareAndSet(Integer.valueOf(500), Integer.valueOf(300), time2, time2 + 1);

        System.out.println("value:" + atomicStamped.getReference());
    }
}
