package com.example.rx.create;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/11
 */
public class Creating {

    public static final void main(String[] args){
        defer();
    }

    private static final void defer(){
        Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                String str = "hello";
                if (str.equals("hello")){
                   return Observable.error(new RuntimeException());
                }
                return Observable.just(str);
            }
        })
        .subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                
            }

            @Override
            public void onNext(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        })
        ;

    }
}
