package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description描述: String ss = "物料[?]出货数量[?]大于可出数量[?]";
 * String[] arr = new String[] { "8932059235532", "4343", "546" };
 * <p>
 * 我要将arr里面的内容分别放到ss里面的[?]里面
 * @Author作者: Kyle
 * @Date日期: 2018/1/11
 */
public class Client {

    public static void main(String args[]) throws ParseException {

        // 当前时间
        Date date = new Date();
        System.out.println("当前时间:" + date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String time = sdf.format(date);
        System.out.println(time);

//解析时间 2016-01-05T15:06:58+0800
        date = sdf.parse(time);
        System.out.println(date);


//        String date = "2016-08-15T16:00:00.000Z";
//        date = date.replace("Z", " UTC");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        Date d = format.parse(date);
//        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

//        System.out.println(format.format(d));

//        String sign = "59:07:71:A2:80:5B:F6:0A:7E:40:E1:BF:D7:BE:B2:50";
//        System.out.println(sign.replace(":", "").toLowerCase());

//        System.out.println("Hello World!");

//        method1();
//        method2();
//        method3();
    }

    private static void method1() {
        System.out.println("方式一:");
        String ss = "物料[?]出货数量[?]大于可出数量[?]";
        String[] arr = new String[]{"8932059235532", "4343", "546"};

        ss = ss.replace("?", "%s");
        ss = String.format(ss, arr);

        System.out.println(ss);
    }


    private static void method2() {
        System.out.println("方式二:");
        String ss = "物料[?]出货数量[?]大于可出数量[?]";
        String[] arr = new String[]{"8932059235532", "4343", "546"};

        String[] ssArr = ss.split("\\?");

        if (arr.length < ssArr.length) {
            for (int i = 0; i < arr.length; i++) {
                ssArr[i] = ssArr[i] + arr[i];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (String s : ssArr) {
            builder.append(s);
        }

        System.out.println(builder);
    }


    private static void method3() {
        System.out.println("方式三:");
        String ss = "物料[?]出货数量[?]大于可出数量[?]";
        String[] arr = new String[]{"8932059235532", "4343", "546"};

        for (String s : arr) {
            ss = ss.replaceFirst("\\?", s);
        }

        System.out.println(ss);
    }

}

