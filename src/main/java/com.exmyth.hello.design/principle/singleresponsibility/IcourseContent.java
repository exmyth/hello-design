package com.exmyth.hello.design.principle.singleresponsibility;

/**
 * 注，本接口主要是获取课程的内容
 */
public interface IcourseContent {
    String getCourseText();//获取课程文本内容
    byte[] getCourseVideo();//获取课程的视频
}