package com.rx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/7/10
 */
public class RxClient {

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);





    public static void main(String[] args) {

        BehaviorSubject<String> subject = BehaviorSubject.create();

        final Observable<String> filter = subject.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                // 如果是quit退出应用
                return "quit".equals(s);
            }
        });


        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                while (true) {
                    String format = sdf.format(new Date());
                    e.onNext(format);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                }

            }
        }).doOnDispose(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("=----------------:doOnDispose");
                atomicBoolean.compareAndSet(false, true);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .compose(new ObservableTransformer<String, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<String> upstream) {
                        return upstream.takeUntil(filter);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(5_000);
                e.onNext("exit");
                Thread.sleep(5_000);
                e.onNext("quit");
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("发送指令：" + s);
                        subject.onNext(s);
                    }
                });


        while (!atomicBoolean.get()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
