package com.exmyth.hello.design.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class CourseIteratorImpl implements CourseIterator{
    private List listCourseAggre ;
    private int position;
    private Course course;
    public CourseIteratorImpl(List listCourseAggre) {
        this.listCourseAggre = listCourseAggre;
    }

    @Override
    public Course nextCourse() {
        System.out.println("返回课程位置"+position);//第一次，没有值，默认为0
        course = (Course) listCourseAggre.get(position);
        position++;
        return course;
    }

    @Override
    public boolean isLastCourse() {
        if(position<listCourseAggre.size()){
            return false;
        }else{
            return true;
        }
    }

}