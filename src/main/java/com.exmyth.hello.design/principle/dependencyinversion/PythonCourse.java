package com.exmyth.hello.design.principle.dependencyinversion;

/**
 * @author exmyth
 * @date 2019-10-15 19:03
 * @description
 */
public class PythonCourse implements ICourse {
    @Override
    public void studyClass() {
        System.out.println("study python class");
    }
}
