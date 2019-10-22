package com.exmyth.hello.design.pattern.structural.decorator.v2;

/**
 * 装饰者同样继承 抽象煎饼类，这是为了方便 ，和煎饼类交互
 *
 * 如果不用构造器的方式，使用set的方式，是否也能达到目的呢？
 */
public class AbstractDecorator extends ABatterCake {

    /**
     * 定义煎饼属性，方便注入
     */
    private ABatterCake aBatterCake;

    /**
     * 通过构造方法，传入煎饼类
     * @param aBatterCake2
     */
    public AbstractDecorator(ABatterCake aBatterCake2){
        this.aBatterCake = aBatterCake2;
    }

    @Override
    public String getDescribe() {
        return aBatterCake.getDescribe();
    }

    @Override
    public int cost() {
        return aBatterCake.cost();
    }
}