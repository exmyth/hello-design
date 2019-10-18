package com.exmyth.hello.design.pattern.creational.builder;

public class Coach {
    CourseBuilder courseBuilder;

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }

    public Course makeCourse(String courseName, String coursePPT, String courseArticle, String courseVideo, String courseQA){
        this.courseBuilder.buildCourseName(courseName);
        this.courseBuilder.buildCourseArticle(courseArticle);
        this.courseBuilder.buildCourseVideo(courseVideo);
        this.courseBuilder.buildCoursePPT(coursePPT);
        this.courseBuilder.buildCourseQA(courseQA);
        return this.courseBuilder.makeCourse();

    }
}