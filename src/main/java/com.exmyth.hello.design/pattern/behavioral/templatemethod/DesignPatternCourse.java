package com.exmyth.hello.design.pattern.behavioral.templatemethod;

/**
 * 设计模式课程，
 * 业务场景：不用编写手记
 */
public class DesignPatternCourse extends ACourse{
    /**
     * 不同子类实现不同包装方法
     */
    @Override
    void packageCourse() {
        System.out.println("后端包装方法实现");
    }
}