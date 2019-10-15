package com.exmyth.hello.design.principle.dependencyinversion;

/**
 * @author exmyth
 * @date 2019-10-15 16:59
 * @description
 */
public class Student {
    private ICourse course;

    public Student() {
    }

    public Student(ICourse course) {
        this.course = course;
    }

    public void setCourse(ICourse course) {
        this.course = course;
    }

    /*
    public void studyJavaClass(){
        System.out.println("Student Study Java Lesson");
    }

    public void studyPreClass(){
        System.out.println("Student Study Pre Lesson");
    }
    */

    /*
    public void studyClass(ICourse course) {
        course.studyClass();
    }
    */

    public void studyClass() {
        course.studyClass();
    }
}
