package com.example.thread;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/16
 */
public class FinalizedClass {

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            new FinalizedBean();
            Runtime.getRuntime().gc();

            System.runFinalization();
        }
    }
}
