package com.exmyth.hello.design.pattern.adapter.objectadapter;

/**
 * 该类是被适配者，想实现目标类的方法
 */
public class Adaptee {
    public void adapteeRequest(){
        System.out.println("被适配器执行它自己的方法。。。");
    }
}