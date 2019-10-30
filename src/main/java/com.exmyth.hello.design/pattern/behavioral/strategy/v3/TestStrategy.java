package com.exmyth.hello.design.pattern.behavioral.strategy.v3;

import com.exmyth.hello.design.pattern.behavioral.strategy.PromotionActivity;
import com.exmyth.hello.design.pattern.behavioral.strategy.PromotionStrategy;

public class TestStrategy {

    public static void main(String [] args){
        String promotionKey = "others";
        PromotionStrategy strategy = PromotionStrategyFactory.getPromotionStrategy(promotionKey);
        PromotionActivity promotionActivity = new PromotionActivity(strategy);
        promotionActivity.execute();
    }
}