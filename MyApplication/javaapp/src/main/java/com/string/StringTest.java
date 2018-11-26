package com.string;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/18
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = "str";
        String str2 = new String("str");
        String str3 = new StringBuilder().append("s").append("t").append("r").toString();


        System.out.println(str1.hashCode());
        System.out.println(str3.hashCode());
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);

    }
}
