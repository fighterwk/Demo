package com.rx;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/9/6
 */
public class Rx2Client {
    public static void main(String[] args) {
        Observable.range(1, 10)
                .observeOn(Schedulers.trampoline())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }
}
