package com.exmyth.hello.design.pattern.behavioral.strategy;

public class LiJianPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("立减10元");
    }
}