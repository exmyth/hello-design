package com.exmyth.hello.design.pattern.behavioral.strategy.v3;

import com.exmyth.hello.design.pattern.behavioral.strategy.FanXianPromotionStrategy;
import com.exmyth.hello.design.pattern.behavioral.strategy.LiJianPromotionStrategy;
import com.exmyth.hello.design.pattern.behavioral.strategy.ManJianPromotionStrategy;
import com.exmyth.hello.design.pattern.behavioral.strategy.PromotionStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author exmyth
 * @date 2019-10-30 11:44
 * @description
 */
public class PromotionStrategyFactory {
    private static final Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new ConcurrentHashMap<>();

    private static final PromotionStrategy defaultPromotionStrategy = new DefaultPromotionStrategy();
    static {
        PROMOTION_STRATEGY_MAP.put("LiJian", new LiJianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put("FanXian", new FanXianPromotionStrategy());
        PROMOTION_STRATEGY_MAP.put("ManJian", new ManJianPromotionStrategy());
    }
    public static PromotionStrategy getPromotionStrategy(String key){
        PromotionStrategy strategy = PROMOTION_STRATEGY_MAP.get(key);
        return strategy == null ? defaultPromotionStrategy : strategy;
    }
}
