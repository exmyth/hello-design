package com.exmyth.hello.design.pattern.observer;

/**
 * @author maming.zhong
 * @date 2019-10-30 17:38
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Course a = new Course("A");

        Teacher t1 = new Teacher("t1");
        Teacher t2 = new Teacher("t2");

        a.addObserver(t1);
        a.addObserver(t2);


        Question question = new Question("理想", "问题");

        a.submit(question);
        System.out.println("--------------");
        a.deleteObserver(t1);
        a.submit(question);
    }
}
