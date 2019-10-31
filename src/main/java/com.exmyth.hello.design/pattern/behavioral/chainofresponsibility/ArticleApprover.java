package com.exmyth.hello.design.pattern.behavioral.chainofresponsibility;

/**
 * @author maming.zhong
 * @date 2019-10-31 20:54
 * @description
 */
public class ArticleApprover extends Approver {
    @Override
    void deploy(Course course) {
        if(course.getArticle() != null){
            System.out.println("deploy article");
            if(approver != null){
                approver.deploy(course);
            }
        } else{
            System.out.println("no article, no deploy");
        }
    }
}
