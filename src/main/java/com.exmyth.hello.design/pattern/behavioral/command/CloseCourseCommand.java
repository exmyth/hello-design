package com.exmyth.hello.design.pattern.behavioral.command;

/**
 * @author maming.zhong
 * @date 2019-10-31 14:07
 * @description
 */
public class CloseCourseCommand implements Command {
    private Course course;

    public CloseCourseCommand(Course course) {
        this.course = course;
    }

    @Override
    public void execute() {
        course.close();
    }
}
