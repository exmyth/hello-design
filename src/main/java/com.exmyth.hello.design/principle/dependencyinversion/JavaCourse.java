package com.exmyth.hello.design.principle.dependencyinversion;

/**
 * @author exmyth
 * @date 2019-10-15 19:02
 * @description
 */
public class JavaCourse implements ICourse {
    @Override
    public void studyClass() {
        System.out.println("study java Class");
    }
}
