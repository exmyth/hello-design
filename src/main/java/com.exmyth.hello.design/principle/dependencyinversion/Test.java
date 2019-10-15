package com.exmyth.hello.design.principle.dependencyinversion;

/**
 * @author exmyth
 * @date 2019-10-15 17:00
 * @description
 */
public class Test {
    public static void main(String[] args) {
        //v1
        /*
        Student Student = new Student();
        Student.studyJavaClass();
        Student.studyPreClass();
        */

        //v2
        /*
        Student student = new Student();
        student.studyClass(new JavaCourse());
        student.studyClass(new PythonCourse());
        */

        Student student = new Student();
        student.setCourse(new JavaCourse());
        student.studyClass();

    }
}
