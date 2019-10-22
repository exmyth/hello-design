package com.exmyth.hello.design.pattern.structural.facade;

/**
 * 测试外观模式
 */
public class Test {
    public static void main(String [] args){
        GiftExchangeService giftExchangeService = new GiftExchangeService();
        PointGift pointGift = new PointGift("熊猫抱枕");
        /**
         * 下边要注入三个子系统,我私下认为这种方式很不常规，测试类我只需要知道交互的类就可以了
         * 也就是说，我只需要知道giftExchange即可
         */
        giftExchangeService.setQualifyService(new QualifyService());
        giftExchangeService.setPointPaymentService(new PointPaymentService());
        giftExchangeService.setShippingService(new ShippingService());
        giftExchangeService.giftExchange(pointGift);
    }
}