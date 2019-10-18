package com.exmyth.hello.design.pattern.creational.abstractfactory;

public class NetArticle implements Article{
    @Override
    public void produce() {
        System.out.println("开始制做点net笔记！");
    }
}