package com.exmyth.hello.design.pattern.creational.builder.v2;

public class Test {
    public static void main(String [] args){
        Course.CourseBuilder courseBuilder = new Course.CourseBuilder();
        Course course =  courseBuilder.buildCourseName("设计模式课程标题").buildCourseVideo("设计模式视频").build();
        System.out.println(course);
    }
}