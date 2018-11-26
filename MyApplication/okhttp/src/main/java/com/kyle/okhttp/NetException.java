package com.kyle.okhttp;

/**
 * @description描述:
 * @author作者: hx
 * @date日期: 2017/12/27
 */

public class NetException extends Exception {
    /**
     * 错误的状态码
     */
    private int errorStateCode;

    public int getErrorStateCode() {
        return errorStateCode;
    }

    public void setErrorStateCode(int errorStateCode) {
        this.errorStateCode = errorStateCode;
    }

    public NetException(String detailMessage, int errorStateCode) {
        super(detailMessage);
        this.errorStateCode = errorStateCode;
    }
}
