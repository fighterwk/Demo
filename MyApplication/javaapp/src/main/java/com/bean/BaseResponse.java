package com.bean;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/21
 */
public class BaseResponse<T> {

    /**
     * Data : true
     * ResultCode : 1
     * ResultMessage : 修改成功
     * IsEncrypt : false
     */

    private T Data;
    private int ResultCode;
    private String ResultMessage;
    private boolean IsEncrypt;

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }

    public boolean isIsEncrypt() {
        return IsEncrypt;
    }

    public void setIsEncrypt(boolean IsEncrypt) {
        this.IsEncrypt = IsEncrypt;
    }
}
