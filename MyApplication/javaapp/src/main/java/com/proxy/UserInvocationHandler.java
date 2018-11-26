package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/15
 */
public class UserInvocationHandler implements InvocationHandler {

    private IUser iUser;

    public UserInvocationHandler(IUser iUser) {
        this.iUser = iUser;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("代理");
        iUser.say();
        return o;
    }
}
