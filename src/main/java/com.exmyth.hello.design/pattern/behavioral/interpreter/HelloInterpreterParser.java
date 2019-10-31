package com.exmyth.hello.design.pattern.behavioral.interpreter;

import java.util.Stack;

/**
 * @author maming.zhong
 * @date 2019-10-30 15:23
 * @description
 */
public class HelloInterpreterParser {
    private static final Stack<Interpreter> stack = new Stack<>();
    public static int parse(String str){
        String[] symbolArray = str.split("\\s+");

        for (String symbol : symbolArray){
            if(OperatorUtil.isNumber(symbol)){
                stack.push(new NumberInterpreter(symbol));
            } else {
                Interpreter first = stack.pop();
                Interpreter second = stack.pop();
                Interpreter interpreter = OperatorUtil.getInterpreter(symbol, first, second);
                stack.push(new NumberInterpreter(interpreter.interpret()));
            }
        }
        Interpreter last = stack.pop();
        return last.interpret();
    }
}
