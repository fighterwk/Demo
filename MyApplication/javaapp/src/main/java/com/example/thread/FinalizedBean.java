package com.example.thread;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/16
 */
public class FinalizedBean {

    static int i = 0;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("第 %d 次 finalize \n", i);
        i ++;
    }
}
