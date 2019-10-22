package com.exmyth.hello.design.pattern.adapter.classadapter;

/**
 * 目标实现类
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("目标实现类自己的方法，，，");
    }
}