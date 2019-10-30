package com.exmyth.hello.design.pattern.behavioral.strategy;

public class ManJianPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("满50元减10元");
    }
}