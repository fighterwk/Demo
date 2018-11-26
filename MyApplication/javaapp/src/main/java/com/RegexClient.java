package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/8/8
 */
public class RegexClient {

    public static void main(String[] args) {


        String str = "laser:[0.21]";
        String regex = "(?<=laser:\\[)(\\d+\\.\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println("找到");
            String group = matcher.group();
            System.out.println(group);
        } else {
            System.out.println("未找到");
        }
    }
}
