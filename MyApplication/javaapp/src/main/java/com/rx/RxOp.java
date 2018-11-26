package com.rx;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/4/20
 */
public class RxOp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Observable.just(1, 4, 2, 5, 7, 2, 3, 1)
                .toSortedList(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer integer, Integer t1) {
                        if (integer == t1) {
                            return 0;
                        } else if (integer > t1) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                })
                .flatMap(new Function<List<Integer>, SingleSource<Integer>>() {
                    @Override
                    public SingleSource<Integer> apply(List<Integer> integers) throws Exception {
                        return new SingleSource<Integer>() {
                            @Override
                            public void subscribe(SingleObserver<? super Integer> observer) {
                                for (Integer integer : integers) {
                                    observer.onSuccess(integer);
                                }
                            }
                        };
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("result: " + integer);
                    }
                });

//        ConnectableObservable<Long> publish = Observable.interval(3, TimeUnit.SECONDS)
//                .publish();
//
//        publish.subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                System.out.println("action1:" + aLong);
//            }
//        });
//
//        Consumer action3 = new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println("action3:" + o);
//            }
//        };
//
//        publish.subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                publish.subscribe(action3);
//                System.out.println("action2:" + aLong);
//            }
//        });
//
//        publish.connect();


//        Observable ob1 = Observable.just(1, 2,3);
//        Observable ob2 = Observable.just("4", "5", "6");
//        ob1.mergeWith(ob2)
//        .doOnNext(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println(o);
//            }
//        }).subscribe();


        // 对象， View、dispose、onClickListener、Observable

//        Observable.range(1, 10)
////                .sample(0, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(":::: >> "+ integer);
//                    }
//                });


//        Observable.range(1, 10)
//                .debounce(10, TimeUnit.SECONDS)
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(System.currentTimeMillis() + " : " + integer);
//                    }
//                })
//                .subscribe();

//        Observable.range(1, 12)
//                .window(2, 3)
//                .doOnNext(new Consumer<Observable<Integer>>() {
//                    @Override
//                    public void accept(Observable<Integer> integerObservable) throws Exception {
//                        integerObservable.subscribe(new Consumer<Integer>() {
//                            @Override
//                            public void accept(Integer integer) throws Exception {
//                                System.out.println(this + " =>>> " + (integer));
//                            }
//                        });
//                    }
//                })
//                .subscribe();


//        Observable.range(1, 10)
//                .cast(String.class)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });

//        Observable.range(1, 10)
//                .groupBy(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return (integer % 2 == 0) ? "双" : "单";
//                    }
//                })
//                .doOnNext(new Consumer<GroupedObservable<String, Integer>>() {
//                    @Override
//                    public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
//                        stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
//                            @Override
//                            public void accept(Integer integer) throws Exception {
//                                System.out.println("key:" + stringIntegerGroupedObservable.getKey());
//                                System.out.println("Value: " + integer);
//                            }
//                        });
//                    }
//                })
//                .subscribe();


        // 模拟 RxView clicks
//        View view = new View();
//        ViewObservable observable = new ViewObservable(view);
//        observable.throttleFirst(2, TimeUnit.SECONDS)
//                .subscribe(new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        System.out.println(Thread.currentThread().getName() + "  接收到数据 : " + o);
//                    }
//                });
//
//        final OnClickListener listener = view.getListener();
//        if (listener != null) {
//            Observable.create(new ObservableOnSubscribe<String>() {
//
//                @Override
//                public void subscribe(ObservableEmitter<String> e) throws Exception {
//                    for (int i = 0; i < 30; i++) {
//                        System.out.println(Thread.currentThread().getName() + "   发射数据...");
//                        e.onNext(String.valueOf(i));
//                        Thread.sleep(1000);
//                    }
//
//                    e.onComplete();
//                }
//            })
//                    .subscribeOn(Schedulers.newThread())
//                    .observeOn(Schedulers.computation())
//                    .doOnNext(new Consumer<String>() {
//                        @Override
//                        public void accept(String s) throws Exception {
//                            listener.onClick(s);
//                        }
//                    })
//                    .subscribe()
//            ;
//
//        }

        while (true) ;
    }
}

// 视图显示
class View {
    OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public OnClickListener getListener() {
        return listener;
    }
}

// 事件回调
interface OnClickListener {

    void onClick(String v);
}


// 被观察对象View
class ViewObservable extends Observable {

    View view;

    public ViewObservable(View view) {
        this.view = view;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        ViewDispose dispose = new ViewDispose(view, observer);
        observer.onSubscribe(dispose);
        view.setListener(dispose);
    }

    class ViewDispose implements Disposable, OnClickListener {

        View view;
        Observer<String> observer;

        AtomicBoolean atomicBoolean = new AtomicBoolean();

        public ViewDispose(View view, Observer<String> observer) {
            this.observer = observer;
            this.view = view;
        }

        @Override
        public void onClick(String v) {
            if (!isDisposed()) {
                this.observer.onNext(v);
            }
        }

        @Override
        public void dispose() {
            if (atomicBoolean.compareAndSet(false, true)) {
                view.setListener(null);
            }
        }

        @Override
        public boolean isDisposed() {
            return atomicBoolean.get();
        }
    }
}

