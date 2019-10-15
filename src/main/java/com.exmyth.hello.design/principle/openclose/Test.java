package com.exmyth.hello.design.principle.openclose;

/**
 * @author exmyth
 * @date 2019-10-15 14:21
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ICourse icourse = new JavaDiscountCourse(96,"java开发教程",298.00);
        System.out.println("购买课程编号为："+icourse.getCourseId()+"；课程名称为："+icourse.getCourseName()+";课程价格为："+((JavaDiscountCourse) icourse).getDiscountPrice());
    }
}
