package com.rx;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/8/16
 */
public class RxRetryWith {
    public static void main(String[] args) {

        Observable.create((ObservableOnSubscribe<String>) e -> {
            System.out.println("请求数据");
            int i = new Random().nextInt(5);
            System.out.println("得到数据:" + i);

            if (i < 2) {
                e.onComplete();
            } else {
                e.onError(new Exception("错误"));
            }

//                e.onComplete();
        }).retryWhen(throwableObservable -> throwableObservable.zipWith(Observable.range(1, 10),
                (throwable, integer) -> integer).flatMap((Function<Integer, ObservableSource<?>>) integer -> {
            System.out.println("重试第" + integer + "次");
            return Observable.timer(3, TimeUnit.SECONDS);
        }))
                .doOnComplete(() -> System.out.println("请求完成"))
                .subscribe();

        while (true) ;

//        Observable.just(1)
//                .zipWith(Observable.just(4, 5, 6),
//                        new BiFunction<Integer, Integer, Integer>() {
//                            @Override
//                            public Integer apply(Integer integer, Integer integer2) throws Exception {
//                                return integer + integer2;
//                            }
//                        })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

//        Observable.just(1)
//                .zipWith(Observable.range(1, 10), (i, i1) -> i + i1)
//                .subscribe( in -> System.out.println(in));


    }
}
