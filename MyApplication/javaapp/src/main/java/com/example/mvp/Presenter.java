package com.example.mvp;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/12
 */
public class Presenter {

    public static final void main(String[] args) {
        Presenter presenter = new Presenter();
        Class<? extends Presenter> aClass = presenter.getClass();
        Layout annotation = aClass.getAnnotation(Layout.class);
        if (annotation != null) {
            int layout = annotation.layout();
            System.out.printf("layout %d\n", layout);
        }
    }
}
