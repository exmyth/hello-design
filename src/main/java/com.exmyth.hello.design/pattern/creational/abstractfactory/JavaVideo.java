package com.exmyth.hello.design.pattern.creational.abstractfactory;

public class JavaVideo implements Video{
    @Override
    public void produce() {
        System.out.println("开始制作java视频");
    }
}