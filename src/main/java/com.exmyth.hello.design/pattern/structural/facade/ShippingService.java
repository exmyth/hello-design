package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 物流子系统
 */
public class ShippingService {
    public String shipGift(PointGift pointGift){
        //其他校验逻辑
        String orderNo = "999";
        System.out.println(pointGift.getGiftName()+"发送物流成功，订单号为"+orderNo);
        return orderNo;
    }
}