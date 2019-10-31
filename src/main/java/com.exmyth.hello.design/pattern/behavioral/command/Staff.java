package com.exmyth.hello.design.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maming.zhong
 * @date 2019-10-31 14:09
 * @description
 */
public class Staff {
    private List<Command> commandList = new ArrayList<>();

    public void addCommand(Command command){
        commandList.add(command);
    }

    public void executeCommand(){
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
