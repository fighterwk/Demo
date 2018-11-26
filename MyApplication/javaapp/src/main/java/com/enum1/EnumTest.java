package com.enum1;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/18
 */
public class EnumTest {

    public static void main(String[] args) {
        Day day = Day.MONDAY;
        switch (day) {
            case TODAY:
                break;
            case MONDAY:
                break;
            case SUNDAY:
                break;
        }
    }

}

enum Day {
    SUNDAY, MONDAY, TODAY
}
