package com.exmyth.hello.design.principle.openclose;

/**
 * @author exmyth
 * @date 2019-10-15 14:17
 * @description
 */
public class JavaCourse implements ICourse {
    private Integer courseId;
    private String courseName;
    private Double coursePrice;

    public JavaCourse(Integer courseId, String courseName, Double coursePrice) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
    }

    @Override
    public Integer getCourseId() {
        return courseId;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public Double getCoursePrice() {
        return coursePrice;
    }
}
