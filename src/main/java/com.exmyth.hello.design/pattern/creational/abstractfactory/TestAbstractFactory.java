package com.exmyth.hello.design.pattern.creational.abstractfactory;

/**
 * 关注产品族
 */
public class TestAbstractFactory {
    /**
     * 制作java类的产品族，制作net类的产品族
     * @param args
     */
    public static void main(String [] args){
       CourseFactory courseFactory1 = new JavaCourseFactory();
       Video video1 = courseFactory1.getVideo();
       Article article1 = courseFactory1.getArticle();
       video1.produce();
       article1.produce();

       CourseFactory courseFactory2 = new NetCourseFactory();
       Video video2 = courseFactory2.getVideo();
       Article article2 = courseFactory2.getArticle();
       video2.produce();
       article2.produce();

    }
}