package com.rx;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/9/7
 */
public class RxInterval {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.trampoline())
                .doOnNext(aLong -> System.out.println(aLong))
                .subscribe();

        while (true);
    }
}
