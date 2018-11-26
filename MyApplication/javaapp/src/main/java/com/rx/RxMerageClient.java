package com.rx;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/8/15
 */
public class RxMerageClient {

    public static void main(String[] args) {
        Observable.mergeArray(Observable.fromArray(1, 2, 4, 5, 3),
                Observable.fromArray(2, 1, 3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.print(integer);
                        System.out.print(" ");
                    }
                });

        while (true);
    }

}
