package com.exmyth.hello.design.pattern.behavioral.command;

/**
 * @author maming.zhong
 * @date 2019-10-31 14:07
 * @description
 */
public class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public void open(){
        System.out.println("open");
    }

    public void close(){
        System.out.println("close");
    }
}
