package com.exmyth.hello.design.pattern.structural.decorator.v1;

/**
 * 加一个鸡蛋的煎饼
 */
public class BatterCakeWithEgg extends BatterCake{
    @Override
    public String getDescribe() {
        return super.getDescribe()+" 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost()+1;
    }
}