package com.exmyth.hello.design.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class Manager implements Employee {

    private String title = "部门经理";
    //该经理所在部门
    private String department;//外部状态

    //该经理汇报内容
    private String reportContent;


    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReportContent() {
        return reportContent;
    }

    //有参构造方法，输入部门
    public Manager(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    /**
     * 实现report方法
     * <p>
     * 如果池子里能取到 该部门的对象，就取，否则，直接创建一个 该部门的对象，并放到从池子里
     */
    @Override
    public void report() {
        System.out.println(">>>>" + this.reportContent + "\n");
    }
}