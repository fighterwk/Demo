package com.decimal;

import java.math.BigDecimal;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/10/24
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal("1000.23464515");
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_FLOOR));
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_CEILING));
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_DOWN));
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_UP));
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
