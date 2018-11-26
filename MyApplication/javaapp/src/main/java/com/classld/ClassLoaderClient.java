package com.classld;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/16
 */
public class ClassLoaderClient {
    public static void main(String[] args) {
//        CustomClass.print();
        Class<CustomClass> customClassClass = CustomClass.class;
        CustomClass customClass;

    }
}

class CustomClass {
    static {
        System.out.println("CustomClass loader");
    }

    public static void print() {
        System.out.println("CustomClass print()");
    }
}
