package com.classloader;

import java.util.UUID;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/7
 */
public class ClassLoaderClient {

    public static void main(String[] args) throws InterruptedException {
//        MyObject object1 = new MyObject();
//        object1.name = "obj1";
//        ClassLoader clz1 = object1.getClass().getClassLoader();
//
//        System.out.println(clz1.getClass().getCanonicalName());



        System.out.println(generateNonceStr());

        // AppClassLoader
        MyObject object2 = null;
        try {
            object2 = MyObject.class.newInstance();
            object2.name = "obj2";
//            ClassLoader clz2 = object2.getClass().getClassLoader();
//            Class<? extends ClassLoader> clz3 = clz2.getClass();
//            System.out.println(clz2.getClass().getCanonicalName());
//            System.out.println(clz3 != null ? clz3.getCanonicalName() : "null");
            printClassLoaderName(object2.getClass().getClassLoader());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    private static void printClassLoaderName(ClassLoader clzLoader) {
        if (clzLoader == null) {
            System.out.println("null");
        } else {
            System.out.println(clzLoader.getClass().getCanonicalName());
            printClassLoaderName(clzLoader.getParent());
        }
    }


    static class MyObject {

        public Object object;
        public String name;

        public MyObject() {
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println(name + "  finalize");
        }
    }
}
