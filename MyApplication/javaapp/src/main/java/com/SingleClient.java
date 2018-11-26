package com;

import sun.misc.Launcher;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/10
 */
class SingleTon {
    private static SingleTon instance = new SingleTon();

    public static int value;
    public static int value1 = 0;

    private SingleTon() {
        value++;
        value1++;
    }

    public static SingleTon getInstance() {
        return instance;
    }

}


class ClassLoader {

    public static String VALUE = "HELLO WORLD";
    public static final String CONSTANT = "CONSTANT";

    static {
        System.out.println("ClassLoader 初始化");
    }
}

class SubClassLoader extends ClassLoader {
    static {
        System.out.println("SubClassLoader 初始化");
    }
}

public class SingleClient {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(SubClassLoader.CONSTANT);  // 子类调用父类的常量不会触发class的初始化，常量存放在jvm内存的本地方法区中

        Launcher launcher = Launcher.getLauncher();
        String s = System.getProperty("java.ext.dirs");
        System.out.println(s);

        System.out.println(System.getProperty("java.class.path"));

        /**
         System.out.println(SubClassLoader.VALUE);   // 子类调用父类中static变量/方法，会触发父类的class初始化，不会触发子类的初始化
         ClassLoader[] classLoaders = new ClassLoader[10]; // 被动引用，不会触发Class初始化

         Class<SubClassLoader> subClassLoaderClass = SubClassLoader.class; // 调用类的class对象不会触发初始化
         String canonicalName = subClassLoaderClass.getCanonicalName();

         Class<?> aClass = Class.forName(canonicalName); // 使用Class.forName静态方法加载类名，会触发class 的初始化

         **/


        // java 中初始化和实例化是有区别的。
        // 初始化是class

//        SingleTon.getInstance();
//        System.out.println("value:" + SingleTon.value);
//        System.out.println("value1:" + SingleTon.value1);
    }
}

