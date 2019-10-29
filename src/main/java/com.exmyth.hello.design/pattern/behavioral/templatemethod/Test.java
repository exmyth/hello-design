package com.exmyth.hello.design.pattern.behavioral.templatemethod;

public class Test {
    public static void main(String [] args){

        System.out.println("=======================后端课程开始");
        ACourse aCourse1 = new DesignPatternCourse();
        aCourse1.makeCourse();
        System.out.println("=======================后端课程结束");


        System.out.println("=======================前端课程开始");
        ACourse aCourse2 = new FECourse();
        aCourse2.makeCourse();
        System.out.println("=======================前端课程结束");
    }
}