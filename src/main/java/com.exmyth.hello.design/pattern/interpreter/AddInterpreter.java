package com.exmyth.hello.design.pattern.interpreter;

/**
 * @author maming.zhong
 * @date 2019-10-30 15:17
 * @description
 */
public class AddInterpreter implements Interpreter {
    private Interpreter first, second;

    public AddInterpreter(Interpreter first, Interpreter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int interpret() {
        return first.interpret() + second.interpret();
    }

    @Override
    public String toString() {
        return "+";
    }
}
