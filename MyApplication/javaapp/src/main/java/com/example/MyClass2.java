package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/1/9
 */
public class MyClass2 {

    public static void main(String[] args) {
        String str = "泉州人 2018年01月08日 17:03";
        String regex = "(泉州人)|(\\d{4}年\\d{2}月\\d{2}日 \\d{2}:\\d{2})";


//        boolean flag = "2018年01月08日 17:03".matches("\\d{4}年\\d{2}月\\d{2}日 \\d{2}:\\d{2}");
//        boolean flag = "11111".matches("\\d{2}");
//        boolean flag = str.matches(regex);
//        System.out.println(flag);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(i + ":" + matcher.group(i));
            }
        }

    }
}

// 定义一个接口
interface MyInterface {

    // 声明一个打印方法
    void print();

    class InnerClass {

        public void print() {
            System.out.println("我是MyInterface接口的内部类哦");
        }
    }
}

// 定义一个接口的实现类
class MyInterfaceImpl implements MyInterface {

    @Override
    public void print(){
        new InnerClass().print();
    }

    // 测试使用
    public static void main(String[] args) {
        new MyInterfaceImpl().print();
    }

}