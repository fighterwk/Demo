package com.example;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * @Description描述: Java 基础知识沉淀
 * @Author作者: Kyle
 * @Date日期: 2018/3/20
 */
public class Subside {

    public static void main(String[] args) throws Exception {
        test_regex();
    }

    private static final String REGEX_PASSWORD = "^((?!\\d+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,16})$";
    static void test_regex(){
        Pattern compile = Pattern.compile(REGEX_PASSWORD);

        // 如何要求的
        System.out.println(compile.matcher("a123A424a").matches());
        // 不满 6位
        System.out.println(compile.matcher("a123A").matches());
        // 全数字
        System.out.println(compile.matcher("111111").matches());
        // 全字母
        System.out.println(compile.matcher("aaaaaQ").matches());
        // 数字开头
        System.out.println(compile.matcher("124sfqA").matches());

    }

    static void decimal(){

        short s = 2;

    }


    static void map() {
//        TreeMap<String, String> map = new TreeMap<>();
//
//        for (int i = 0; i < 10; i++) {
//            String key = String.valueOf(new Random().nextInt(200));
//            System.out.println("key:" + key);
//
//            map.put(key, String.valueOf(i));
//        }

//        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
//            System.out.println(stringStringEntry.getKey() + " > " + stringStringEntry.getValue());
//        }


        HashMap hashMap;
//        hashMap.put()

    }


    /**
     * 线程锁
     */
    static void threadLock() {

        final String content = "1000";

        final ReentrantLock lock = new ReentrantLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("主线程获取锁");
//                    lock.lock();
                    lock.lockInterruptibly();
                    System.out.println("锁响应中断");
                    System.out.println("执行完成:" + content);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("主线程释放锁");
                }


            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("辅助线程获取锁");
                lock.lock();  // 获得锁,如果没有被执行就执行下去
                System.out.println("辅助线程执行");
                for (int i = 0; i < 1000000; i++) {
                    for (int i1 = 0; i1 < i; i1++) {
                        for (int i2 = 0; i2 < i1; i2++) {

                        }
                    }
                }

                lock.unlock();  // 释放锁
                System.out.println("辅助线程释放了锁");

            }
        }).start();
    }

    /**
     * 线程锁，在调用object.wait()方法时，会去获取当前线程中的对象锁，如果有锁就进行等待，如果没有锁就会抛出
     * InterruptedException
     * <p>
     * 判断线程中的对象是否有锁，可以通过 Thread.holdsLock(obj)方法进行检测.
     */
    static void objectWait() {

        final String content = "1000";

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (content) {
                    try {
                        content.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("执行完成:" + content);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    synchronized (content) {
                        content.notify();
                    }
                    System.out.println("lock:" + Thread.holdsLock(content));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 基础数据包装
     */
    static void baseDataWrapper() {
        {
            int iNum = 100;   // 基本数据类型
            Integer iNum2 = iNum;  // 自动装箱
            int iNum3 = iNum2;  // 自动拆箱
        }

        {
//            Integer iNum = 127;
//            Integer iNum2 = 127;

            int iNum = new Integer(127);
            int iNum2 = new Integer(127);

            System.out.println(iNum == iNum2);
//            System.out.println(iNum2.equals(iNum));
        }


    }

    /**
     * 异常问题处理:
     * 1. try{}catch{}中如果有return finally方法块会执行吗?
     * 2. 在try{}方法块中如果有return并且执行到该位置时，finally方法块中的方法会执行
     * 3. 在catch{}方法块中如果有return并且执行到该位置时，finally方法块中的方法会执行
     * <p>
     * 总结:在try{}catch(){}方法块执行完成后，有finally{}代码块都会执行。
     */
    static void exceptionHandler() {
        try {
            System.out.println("try 方法块执行");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("catch 方法块执行");
            System.out.println("执行return");
            return;
        } finally {
            System.out.println("finally 方法块执行");
        }
    }
}
