package com.kyle.okhttp;

/**
 * @Description描述:
 * @Author作者: liuyixin
 * @Date日期: 2016/8/19
 */
public interface NetWorkCallBack {
    void callBack(String data, int tag);

    void callError(Throwable e, int tag);
}
