package com.gson;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/28
 */
public class ObjBean {

    /**
     * Data : 1
     * ResultCode : 1
     * ResultMessage : 修改成功
     * IsEncrypt : false
     */

    private int Data;
    private int ResultCode;
    private String ResultMessage;
    private boolean IsEncrypt;

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
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
        System.out.println("调用 setResultMessage()");
        this.ResultMessage = ResultMessage;
    }

    public boolean isIsEncrypt() {
        return IsEncrypt;
    }

    public void setIsEncrypt(boolean IsEncrypt) {
        this.IsEncrypt = IsEncrypt;
    }
}
