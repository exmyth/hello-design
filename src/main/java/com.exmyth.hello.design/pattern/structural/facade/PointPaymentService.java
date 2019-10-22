package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 积分校验类，校验积分是否足够
 */
public class PointPaymentService {

    public boolean pay(PointGift pointGift){
       System.out.println(pointGift.getGiftName()+"积分校验通过！");
       return true;
    }
}