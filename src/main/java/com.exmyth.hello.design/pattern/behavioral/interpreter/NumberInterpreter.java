package com.exmyth.hello.design.pattern.behavioral.interpreter;

/**
 * @author maming.zhong
 * @date 2019-10-30 15:19
 * @description
 */
public class NumberInterpreter implements Interpreter {
    private int first;

    public NumberInterpreter(int num) {
        this.first = num;
    }

    public NumberInterpreter(String str) {
        this.first = Integer.parseInt(str);
    }

    @Override
    public int interpret() {
        return first;
    }

    @Override
    public String toString() {
        return String.valueOf(first);
    }

}