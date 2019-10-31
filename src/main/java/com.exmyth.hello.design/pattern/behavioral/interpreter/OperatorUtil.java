package com.exmyth.hello.design.pattern.behavioral.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author exmyth
 * @date 2019-10-30 15:25
 * @description
 */
public class OperatorUtil {
    private static final Pattern number = Pattern.compile("\\d+");
    public static final boolean isNumber(String symbol){
        Matcher matcher = number.matcher(symbol);
        return matcher.matches();
    }

    public static Interpreter getInterpreter(String symbol, Interpreter first, Interpreter second) {
        if("+".equals(symbol)){
            return new AddInterpreter(first, second);
        } else if("*".equals(symbol)){
            return new MultiInterpreter(first, second);
        }
        return null;
    }
}
