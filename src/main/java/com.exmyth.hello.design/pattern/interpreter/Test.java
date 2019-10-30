package com.exmyth.hello.design.pattern.interpreter;

/**
 * @author maming.zhong
 * @date 2019-10-30 15:14
 * @description
 */
public class Test {

    public static void main(String[] args) {
        //100 + 11 = 111
        //111 *  6 = 666
        String str = "6 100 11 + *";
        int num = HelloInterpreterParser.parse(str);
        System.out.println(num);
    }
}
