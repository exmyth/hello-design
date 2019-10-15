package com.exmyth.hello.design.principle.dependencyinversion;

/**
 * @author exmyth
 * @date 2019-10-15 17:00
 * @description
 */
public class Test {
    public static void main(String[] args) {
        //v1 普通方法调取java类
        /*
        Student Student = new Student();
        Student.studyJavaClass();
        Student.studyPreClass();
        */

        //v2 面向接口编程 传参
        /*
        Student student = new Student();
        student.studyClass(new JavaCourse());
        student.studyClass(new PythonCourse());
        */

        //v3 面向接口编程 构造函数
        Student student = new Student();
        student.setCourse(new JavaCourse());
        student.studyClass();

        //4 面向接口编程 set方法
        Student stu = new Student();
        stu.setCourse(new PythonCourse());
        stu.studyClass();
    }
}
