package com.exmyth.hello.design.pattern.behavioral.strategy.v3;

import com.exmyth.hello.design.pattern.behavioral.strategy.PromotionStrategy;

/**
 * @author maming.zhong
 * @date 2019-10-30 11:46
 * @description
 */
public class DefaultPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("default");
    }
}
