package com.exmyth.hello.design.principle.singleresponsibility;

public class Course implements IcourseAction,IcourseContent{
    @Override
    public void beginStudy() {

    }

    @Override
    public void quitStudy() {

    }

    @Override
    public String getCourseText() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }
}