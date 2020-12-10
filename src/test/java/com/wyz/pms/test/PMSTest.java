package com.wyz.pms.test;

import com.wyz.pms.common.util.CalculateUtil;

import java.math.BigDecimal;

public class PMSTest {

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("2.10");
        BigDecimal bigDecimal2 = new BigDecimal("2.20");
        BigDecimal decimal = CalculateUtil.moneyAdd(bigDecimal1, bigDecimal2);
        System.out.println("总价："+decimal);

    }
}
