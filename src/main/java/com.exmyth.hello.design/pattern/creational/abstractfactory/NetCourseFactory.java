package com.exmyth.hello.design.pattern.creational.abstractfactory;

public class NetCourseFactory implements CourseFactory{
    @Override
    public Video getVideo() {
        return new NetVideo();
    }

    @Override
    public Article getArticle() {
        return new NetArticle();
    }
}