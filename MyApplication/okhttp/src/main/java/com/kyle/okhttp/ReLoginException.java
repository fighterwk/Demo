package com.kyle.okhttp;

/**
 * Created by Lixiaoge on 2016/12/6.
 */

public class ReLoginException extends Exception {
    public ReLoginException() {
    }

    public ReLoginException(String detailMessage) {
        super(detailMessage);
    }
}
