package com.kyle.myapplication.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kyle.myapplication.BR;


/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/9/30
 */
public class PersonInfoBean extends BaseObservable{
    private String name;
    private int sex;
    private int age;

    public PersonInfoBean(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
