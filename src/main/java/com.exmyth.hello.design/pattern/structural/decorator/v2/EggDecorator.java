package com.exmyth.hello.design.pattern.structural.decorator.v2;

public class EggDecorator extends AbstractDecorator{

    /**
     * 传入
     * 为什么要实现构造器，父类已经没有无参构造器了
     * @param aBatterCake2
     */
    public EggDecorator(ABatterCake aBatterCake2) {
        super(aBatterCake2);
    }

    @Override
    public String getDescribe() {
        return super.getDescribe() + " 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost()+1;
    }
}