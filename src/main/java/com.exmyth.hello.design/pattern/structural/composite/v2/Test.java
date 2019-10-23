package com.exmyth.hello.design.pattern.structural.composite.v2;

public class Test {

    public static void main(String [] args){
        CatalogComponent totalCourse = new CourseCatalog("课程总目录",1);
        CatalogComponent linuxCourse = new Course("linux课程",11);
        CatalogComponent windowsCourse = new Course("windows操作系统课程",22);
        CatalogComponent javaCatalog = new CourseCatalog("java课程目录",2);
        CatalogComponent javaHibernate = new Course("hibernate课程",33);
        CatalogComponent javaStruct2 = new Course("Struct2课程",44);
        CatalogComponent javaSpring = new Course("Spring课程",55);
        javaCatalog.addCatalog(javaHibernate);
        javaCatalog.addCatalog(javaStruct2);
        javaCatalog.addCatalog(javaSpring);
        totalCourse.addCatalog(linuxCourse);
        totalCourse.addCatalog(windowsCourse);
        totalCourse.addCatalog(javaCatalog);
        totalCourse.printCatalog();
    }
}