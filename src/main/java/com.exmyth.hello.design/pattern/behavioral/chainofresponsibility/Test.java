package com.exmyth.hello.design.pattern.behavioral.chainofresponsibility;

/**
 * @author exmyth
 * @date 2019-10-31 20:55
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ArticleApprover articleApprover = new ArticleApprover();
        VideoApprover videoApprover = new VideoApprover();
        Course course = new Course("a", "v", "t");

        articleApprover.nextApprover(videoApprover);
        articleApprover.deploy(course);

        System.out.println();

        articleApprover.deploy(new Course(null, "v", "t"));

        System.out.println();

        articleApprover.deploy(new Course("a", null, "t"));
    }
}
