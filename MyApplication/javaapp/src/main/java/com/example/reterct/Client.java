package com.example.reterct;

import com.example.reterct.impl.MyModel;
import com.example.reterct.impl.MyPresenter;
import com.example.reterct.impl.MyView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/9
 */
public class Client {

    public static final void main(String[] args) {
        MyPresenter presenter = new MyPresenter<MyView, MyModel>() {
        };
        Class<? extends MyPresenter> clz = presenter.getClass();
        Type genericSuperclass = clz.getGenericSuperclass();
        if (genericSuperclass != null) {
            if (genericSuperclass instanceof Class) {
                System.out.println("type is class");
                System.out.println(((Class) genericSuperclass).getCanonicalName());
            } else if (genericSuperclass instanceof ParameterizedType) {
                System.out.println("type is ParameterizedType");
            } else {
                System.out.println("unkown type");
            }
        }
    }
}
