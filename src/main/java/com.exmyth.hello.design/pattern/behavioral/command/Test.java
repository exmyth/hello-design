package com.exmyth.hello.design.pattern.behavioral.command;

/**
 * @author exmyth
 * @date 2019-10-31 14:26
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Staff staff = new Staff();
        Course a = new Course("A");
        staff.addCommand(new OpenCourseCommand(a));
        staff.addCommand(new CloseCourseCommand(a));
        staff.executeCommand();
    }
}
