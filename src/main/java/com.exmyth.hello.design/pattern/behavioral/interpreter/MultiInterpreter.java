package com.exmyth.hello.design.pattern.behavioral.interpreter;

/**
 * @author exmyth
 * @date 2019-10-30 15:19
 * @description
 */
public class MultiInterpreter implements Interpreter {

    private Interpreter first, second;

    public MultiInterpreter(Interpreter first, Interpreter second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int interpret() {
        return first.interpret() * second.interpret();
    }

    @Override
    public String toString() {
        return "*";
    }
}