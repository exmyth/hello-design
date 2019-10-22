package com.exmyth.hello.design.pattern.structural.adapter.objectadapter;

public class Test {
    public static void main(String [] args){
        //打印目标类的方法
        Target target = new ConcreteTarget();
        target.request();

        //打印适配器的方法
        Adapter adapter = new Adapter();
        adapter.request();
    }
}