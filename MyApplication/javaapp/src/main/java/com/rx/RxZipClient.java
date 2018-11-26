package com.rx;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/8/23
 */
public class RxZipClient {
    public static void main(String[] args) {
        Observable<Integer> ob1 = Observable.fromArray(1, 2, 3, 4, 5, 6);
        Observable<Integer> ob2 = Observable.fromArray(11, 12, 13, 14, 15, 16);

        ob1.zipWith(ob2
                , new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Exception {
                        return integer + "+" + integer2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.print(s);
                        System.out.print(" ");
                    }
                });

        while (true) ;
    }

}
