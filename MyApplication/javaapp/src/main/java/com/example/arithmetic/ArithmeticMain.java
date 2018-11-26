package com.example.arithmetic;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/12/26
 */
public class ArithmeticMain {

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 4; i < 100; i++) {
//            System.out.println(String.format("%d%s素数", i, isPrime(i) ? "是" : "不是"));
//        }

//        for (int i = 2; i < 1000; i++) {
//            if (isMopNumber(i)){
//
//                System.out.printf("猫扑数字:%d\n", i);
//            }
////            System.out.println(String.format("%d%s猫扑数字", i, isMopNumber(i) ? "是" : "不是"));
//        }

//        double asin = Math.asin(Math.sin(30));
//        System.out.println("asin(30) = " + asin);

        byte[] data = new byte[30 * 1024 * 1024];
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = Byte.decode(random.nextInt(16) + "");
        }

        data = Base64.getEncoder().encode(data);


//        String  encode = Base64.getEncoder().encodeToString(data);
        String encode = new String(data, Charset.defaultCharset());
//        System.out.println(String.format("{\"imageFile\":\"%s\"}", encode).getBytes().length);
//        System.out.println(encode + ".");

        Thread.sleep(1000);
    }

    // 猫扑数
    public static boolean isMopNumber(int i) {
        if (i < 10) return i == 2;
        else return (i % 10 == 3) && isMopNumber(i / 10);
    }

    static boolean isPrime(long number) {
        if (number < 2)
            // 使用平均值
            for (long i = 3; i < Math.sqrt(number); i = i + 2) {
                if (number % i == 0) {
                    return false;
                }
            }

        return true;
    }
}
