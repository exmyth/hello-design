package com.exmyth.hello.design.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class CourseAggregateImpl implements CourseAggregate {

    private List list1;
    public CourseAggregateImpl() {
        this.list1 = new ArrayList();
    }


    @Override
    public void addCourse(Course course) {
        list1.add(course);
    }
    @Override
    public void removeCourse(Course course) {
        list1.remove(course);
    }

    @Override
    public CourseIterator getCourseIterator() {
        return new CourseIteratorImpl(list1);
    }
}