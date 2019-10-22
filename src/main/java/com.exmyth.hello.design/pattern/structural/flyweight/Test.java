package com.exmyth.hello.design.pattern.structural.flyweight;

public class Test {
    public static void main(String [] args){
        String [] departmentArray = {"业务部","研发部","管理部"};
        String department = "";
        for (int i = 0;i < 10;i++){
            int departmentNum = (int)(Math.random()*departmentArray.length);
            department =departmentArray[departmentNum];
            Employee manager = EmployFactory.getManager(department);
            manager.report();
        }
    }
}