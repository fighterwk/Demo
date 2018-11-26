package com.example.base;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/12/7
 */
public class OperationTest {

    public static void main(String[] args) {
        testBitOperation();
    }


    private static void testCalcPoint() {
//        int radius = 20;
//        int angle = 30;
//
//        int p1 = radius * Math.


    }

    // 测试位运算
    private static void testBitOperation() {
        long num1 = 1;
        System.out.println(Long.toBinaryString(num1));
        int bit = 35;
        long result = num1 << bit;
        System.out.println(Long.toBinaryString(result));

        System.out.println(num1 + "<<" + bit + " = " + result);
    }
}
