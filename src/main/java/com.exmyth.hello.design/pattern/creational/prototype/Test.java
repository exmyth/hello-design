package com.exmyth.hello.design.pattern.creational.prototype;

/**
 * 测试类：
 */
public class Test {

    /**
     * 1    测试使用原型模式（克隆之前）
     * @param args
     */
    public static void main(String [] args){
        Mail mail = new Mail();
        mail.setContent("初始化模版");

        for(int i = 0;i < 10;i++){
            mail.setName("姓名"+i);
            mail.setContent("内容"+i);
            mail.setEmailAddress(i+"@imooc.com");
            MailUtil.sendMail(mail);
        }
        MailUtil.saveOriginMail(mail);
    }

}