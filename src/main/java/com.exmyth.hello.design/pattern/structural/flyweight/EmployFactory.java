package com.exmyth.hello.design.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建员工工厂类，
 * 我第一次做的时候就忘了创建这个类
 */
public class EmployFactory {
    //此处应用了 final修饰 引用成员变量，引用对象的内容可以修改，但是引用地址不可以修改
    private static final Map<String,Employee> EMPLOYEE_MAP = new HashMap<String,Employee>();

    public static Employee getManager(String department){

        Manager manager = (Manager) EMPLOYEE_MAP.get(department);
        if(manager==null){
            System.out.println("创建部门经理"+department);
            String reportContent = "开始演讲报告";
            manager = new Manager(department);
            manager.setReportContent(reportContent);
            EMPLOYEE_MAP.put(department,manager);
        }else{
            System.out.println("从池子里取部门经理"+manager.getDepartment());
            /*
            reportContent = "开始演讲报告";
            ((Manager) manager).setReportContent(reportContent);
            System.out.println("演讲报告内容为："+reportContent);
            */
        }
        return manager;
    }
}