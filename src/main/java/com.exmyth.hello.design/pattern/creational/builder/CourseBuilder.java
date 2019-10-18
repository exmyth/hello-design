package com.exmyth.hello.design.pattern.creational.builder;

/**
 * 这里使用 抽象类，完全是我练手用的，用实时接口也可以
 */
public abstract class CourseBuilder{

    public abstract void buildCourseName(String courseName);
    public abstract void buildCoursePPT(String coursePPT);
    public abstract void buildCourseArticle(String courseArticle);
    public abstract void buildCourseVideo(String courseVideo);
    public abstract void buildCourseQA(String courseQA);

    public abstract  Course makeCourse();

}