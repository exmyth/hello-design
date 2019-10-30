package com.exmyth.hello.design.pattern.behavioral.strategy.v2;

import com.exmyth.hello.design.pattern.behavioral.strategy.LiJianPromotionStrategy;
import com.exmyth.hello.design.pattern.behavioral.strategy.ManJianPromotionStrategy;
import com.exmyth.hello.design.pattern.behavioral.strategy.PromotionActivity;

public class TestStrategy {

    public static void main(String [] args){
        PromotionActivity promotionActivity = null;
        String promotionKey = "ManJian";
        if("LiJian".equals(promotionKey)){
            promotionActivity = new PromotionActivity(new LiJianPromotionStrategy());
        }else if("ManJian".equals(promotionKey)){
            promotionActivity = new PromotionActivity(new ManJianPromotionStrategy());
        }
        promotionActivity.execute();
    }
}