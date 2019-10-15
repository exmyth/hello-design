package com.exmyth.hello.design.principle.openclose;

/**
 * @author exmyth
 * @date 2019-10-15 14:31
 * @description
 */
public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer courseId, String courseName, Double coursePrice) {
        super(courseId, courseName, coursePrice);
    }

    public Double getDiscountPrice() {
        return super.getCoursePrice()*0.8;
    }

    public Double getOriginalPrice() {
        return super.getCoursePrice();
    }
}
