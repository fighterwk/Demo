package com.example.gson.bean;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/20
 */
public class UserBean {

    private String UserID;
    private int UserType;
    private String Email;
    private String Mobile;
    private String UserToken;
    private String UserCNName;
    private String UserENName;
    private String PhotoUrl;
    private int IDType;
    private int BJCA_ClientID;
    private String IDNumber;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int UserType) {
        this.UserType = UserType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getUserToken() {
        return UserToken;
    }

    public void setUserToken(String UserToken) {
        this.UserToken = UserToken;
    }

    public String getUserCNName() {
        return UserCNName;
    }

    public void setUserCNName(String UserCNName) {
        this.UserCNName = UserCNName;
    }

    public String getUserENName() {
        return UserENName;
    }

    public void setUserENName(String UserENName) {
        this.UserENName = UserENName;
    }

    public String getPhotoUrl() {
        return PhotoUrl;
    }

    public void setPhotoUrl(String PhotoUrl) {
        this.PhotoUrl = PhotoUrl;
    }

    public int getIDType() {
        return IDType;
    }

    public void setIDType(int IDType) {
        this.IDType = IDType;
    }

    public int getBJCA_ClientID() {
        return BJCA_ClientID;
    }

    public void setBJCA_ClientID(int BJCA_ClientID) {
        this.BJCA_ClientID = BJCA_ClientID;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "UserID='" + UserID + '\'' +
                ", UserType=" + UserType +
                ", Email='" + Email + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", UserToken='" + UserToken + '\'' +
                ", UserCNName='" + UserCNName + '\'' +
                ", UserENName='" + UserENName + '\'' +
                ", PhotoUrl='" + PhotoUrl + '\'' +
                ", IDType=" + IDType +
                ", BJCA_ClientID=" + BJCA_ClientID +
                ", IDNumber='" + IDNumber + '\'' +
                '}';
    }
}
