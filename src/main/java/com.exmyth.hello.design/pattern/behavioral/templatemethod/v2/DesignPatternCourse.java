package com.exmyth.hello.design.pattern.behavioral.templatemethod.v2;

/**
 * 设计模式课程，
 * 业务场景：不用编写手记
 */
public class DesignPatternCourse extends ACourse{
    /**
     * 钩子方法，后端课程加入编写手记方法
     * @return
     */
    @Override
    protected boolean needMakeArticle() {
        return true;
    }

    /**
     * 不同子类实现不同包装方法
     */
    @Override
    void packageCourse() {
        System.out.println("后端包装方法实现");
    }
}