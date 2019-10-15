package com.exmyth.hello.design.principle.singleresponsibility;

/**
 * 这个接口和获取内容的接口有先后顺序，只有开始学习，才能获取内容，如果退出学习，就不能在获取内容了，
 * 由于职责不同，所以设计两个接口符合单一职责原则
 */
public interface IcourseAction {
    void beginStudy();
    void quitStudy();
}