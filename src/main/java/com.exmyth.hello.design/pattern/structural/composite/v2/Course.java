package com.exmyth.hello.design.pattern.structural.composite.v2;

public class Course extends CatalogComponent {
    private String sCourseName;
    private double sCoursePrice;

    public Course(String sCourseName, double sCoursePrice) {
        this.sCourseName = sCourseName;
        this.sCoursePrice = sCoursePrice;
    }

    @Override
    public String getName(CatalogComponent catalogComponent) {
        return this.sCourseName;
    }

    @Override
    public double getPrice(CatalogComponent catalogComponent) {
        return this.sCoursePrice;
    }

    @Override
    public void printCatalog() {
        System.out.println("课程名称为"+sCourseName+",  课程价格为"+sCoursePrice);
    }
}