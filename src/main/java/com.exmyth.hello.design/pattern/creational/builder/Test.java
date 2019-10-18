package com.exmyth.hello.design.pattern.creational.builder;

public class Test {
    public static void main(String [] args){
        Coach coach = new Coach();
        Course course = new Course();
        CourseBuilder courseBuilder = new CourseActualBuilder();
        coach.setCourseBuilder(courseBuilder);

        course=coach.makeCourse("设计模式","设计模式PPT","设计模式标题","设计模式视频","设计模式课程问答");
        System.out.println(course.toString());
    }
}