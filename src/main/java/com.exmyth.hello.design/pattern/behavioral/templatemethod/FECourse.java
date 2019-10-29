package com.exmyth.hello.design.pattern.behavioral.templatemethod;

public class FECourse extends ACourse{
    /**
     * 不同子类实现不同包装方法
     */
    @Override
    void packageCourse() {
        System.out.println("提供课程前端代码");
        System.out.println("提供课程图片素材");
    }
}