package com.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/15
 */
public class App {

    public static void main(String[] args) {

        IUser iUser = (IUser) Proxy.newProxyInstance(IUser.class.getClassLoader(),
                new Class[]{IUser.class},
                new UserInvocationHandler(new KyleUser()));

        iUser.say();
    }
}
