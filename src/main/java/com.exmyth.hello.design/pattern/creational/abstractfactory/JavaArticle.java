package com.exmyth.hello.design.pattern.creational.abstractfactory;

public class JavaArticle implements Article{
    @Override
    public void produce() {
        System.out.println("开始录入java笔记！");
    }
}