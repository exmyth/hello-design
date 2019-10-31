package com.exmyth.hello.design.pattern.behavioral.command;

/**
 * @author exmyth
 * @date 2019-10-31 14:07
 * @description
 */
public class OpenCourseCommand implements Command {
    private Course course;

    public OpenCourseCommand(Course course) {
        this.course = course;
    }

    @Override
    public void execute() {
        course.open();
    }
}
