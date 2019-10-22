package com.exmyth.hello.design.pattern.structural.decorator.v1;

/**
 * 加一个火腿和一个鸡蛋的煎饼
 */
public class BatterCakeWithEggSausage extends BatterCakeWithEgg{

    @Override
    public String getDescribe() {
        return super.getDescribe() + " 加一个火腿";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}