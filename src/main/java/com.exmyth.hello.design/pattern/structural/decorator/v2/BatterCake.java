package com.exmyth.hello.design.pattern.structural.decorator.v2;

public class BatterCake extends ABatterCake{

    @Override
    public String getDescribe() {
        return "一个煎饼";
    }

    @Override
    public int cost() {
        return 8;
    }
}