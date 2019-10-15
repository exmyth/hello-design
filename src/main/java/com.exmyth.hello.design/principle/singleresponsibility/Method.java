package com.exmyth.hello.design.principle.singleresponsibility;

public class Method {
    /**
     * 正确，单一职责原则，修改用户的名称
     * @return
     */
    public String updateUserName(){
        return "";
    }

    /**
     * 正确，单一职责原则,修改用户的密码
     * @return
     */
    public String updateUserPassWord(){
        return "";
    }

    /**
     * 错误，作为对比
     * @return
     */
    public String updateUserInfo(String userId,String gender, boolean bool){
        if(bool){

        } else {

        }

        return " ";
    }
    /**
     * 错误，作为对比
     * @param userId
     * @return
     */
    public String updateUserInfo2(String userId){
        return " ";
    }
}