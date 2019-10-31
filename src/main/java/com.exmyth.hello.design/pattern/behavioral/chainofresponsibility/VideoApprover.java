package com.exmyth.hello.design.pattern.behavioral.chainofresponsibility;

/**
 * @author maming.zhong
 * @date 2019-10-31 20:54
 * @description
 */
public class VideoApprover extends Approver {
    @Override
    void deploy(Course course) {
        if(course.getVideo() != null){
            System.out.println("deploy video");
            if(approver != null){
                approver.deploy(course);
            }
        } else {
            System.out.println("no video, no deploy");
        }
    }
}
