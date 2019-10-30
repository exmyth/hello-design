package com.exmyth.hello.design.pattern.behavioral.iterator;

/**
 * 迭代器实体类
 */
public class Course {

    /**
     * 方便for循环打印使用
     */
    public String getName() {
        return name;
    }

    private String name;//定义属性名称

    /**
     * 方便注值使用
     * @param name
     */
    public Course(String name) {
        this.name = name;
    }
}