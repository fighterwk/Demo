package com.example.gson.bean;

/**
 * @Description描述: 省市区
 * @Author作者: Kyle
 * @Date日期: 2017/10/19
 */
public class RegionBean {

    /**
     * Id : 1
     * Code : 110000
     * ParentCode : 0
     * Name : 北京市
     * Level : 1
     * Latitude : 39.929986
     * Longitude : 116.395645
     */

    private int Id;
    private int Code;
    private int ParentCode;
    private String Name;
    private int Level;
    private String Latitude;
    private String Longitude;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public int getParentCode() {
        return ParentCode;
    }

    public void setParentCode(int ParentCode) {
        this.ParentCode = ParentCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public String toString() {
        return "RegionBean{" +
                "Id=" + Id +
                ", Code=" + Code +
                ", ParentCode=" + ParentCode +
                ", Name='" + Name + '\'' +
                ", Level=" + Level +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                '}';
    }
}
