package com.exmyth.hello.design.pattern.behavioral.strategy;

public class FanXianPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现10元到木木网");
    }
}