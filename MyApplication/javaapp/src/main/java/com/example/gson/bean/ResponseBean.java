package com.example.gson.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/20
 */
public class ResponseBean<T> {
    @SerializedName("ResultCode")
    int resultCode ;
    @SerializedName("ResultMessage")
    String resultMessage;
    @SerializedName("Data")
    T data;


    public T getData() {
        return data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "resultCode=" + resultCode +
                ", resultMessage='" + resultMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
