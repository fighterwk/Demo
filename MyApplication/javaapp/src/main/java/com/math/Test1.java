package com.math;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/16
 */
public class Test1 {
    public static void main(String[] args) {
        double degress = 45.0;
        double radians = Math.toRadians(degress);

        System.out.println("radians:" + Math.sin(radians));

        System.out.println(":" + Math.toDegrees(Math.asin(Math.sin(radians))));

    }
}
