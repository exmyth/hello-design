package com.exmyth.hello.design.pattern.behavioral.strategy;

public class TestStrategy {
    public static void main(String [] args){
        PromotionActivity promotionActivityLJ = new PromotionActivity(new LiJianPromotionStrategy());
        PromotionActivity promotionActivityMJ = new PromotionActivity(new ManJianPromotionStrategy());

        promotionActivityLJ.execute();
        promotionActivityMJ.execute();
    }
}