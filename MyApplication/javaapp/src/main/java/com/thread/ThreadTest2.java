package com.thread;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/22
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        Member member;
        for (int i = 0; i < 20; i++) {
            final int flag = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("flag:" + flag);
                }
            }, "thread:" + i);
            thread.start();
            if (flag % 3 == 0){
                thread.yield();  // 3的倍数的整数不和其它线程抢资源
            }
        }
    }

    class Member {

    }
}
