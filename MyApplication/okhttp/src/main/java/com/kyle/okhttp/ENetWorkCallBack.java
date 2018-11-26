package com.kyle.okhttp;

/**
 * @Description描述: 扩展网络回调接口
 * @Author作者: Kyle
 * @Date日期: 2017-10-24
 */
public interface ENetWorkCallBack extends NetWorkCallBack {

    /**
     * 请求开始
     *
     * @param tag
     */
    void start(int tag);
}

