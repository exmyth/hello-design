package com.exmyth.hello.design.pattern.structural.decorator.v2;

public class SauseDecorator extends  AbstractDecorator{
    public SauseDecorator(ABatterCake aBatterCake2) {
        super(aBatterCake2);
    }

    @Override
    public String getDescribe() {
        return super.getDescribe()+" 加一个香肠";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}