package com.exmyth.hello.design.pattern.creational.prototype.v3;

/**
 * 一种常用的原型模式
 * 通过抽象类来实现原型模式
 */
public abstract class A implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}