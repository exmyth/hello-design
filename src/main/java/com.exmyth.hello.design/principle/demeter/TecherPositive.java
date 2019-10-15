package com.exmyth.hello.design.principle.demeter;

import java.util.ArrayList;
import java.util.List;

public class TecherPositive {
    public void countCourse(){
        List<CourseNegated> list = new ArrayList<CourseNegated>();
        for(int i= 0;i<20;i++){
            list.add(new CourseNegated());
        }
        System.out.println("书的总数量为："+list.size());
    }
}