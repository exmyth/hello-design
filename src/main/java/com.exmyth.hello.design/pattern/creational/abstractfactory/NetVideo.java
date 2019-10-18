package com.exmyth.hello.design.pattern.creational.abstractfactory;

public class NetVideo implements Video{
    @Override
    public void produce() {
        System.out.println("开始只做点net视频！");
    }
}