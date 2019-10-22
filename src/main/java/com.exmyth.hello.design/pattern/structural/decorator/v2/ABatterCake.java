package com.exmyth.hello.design.pattern.structural.decorator.v2;

/**
 * 做成从抽象方法，方便煎饼实体类和装饰类继承
 *
 * 可以做成接口，让其他类实现吗？
 */
public abstract class ABatterCake {
    protected abstract String getDescribe();
    protected abstract int cost();
}